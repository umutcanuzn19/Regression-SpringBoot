package codefirst.spring.demo.service;

import codefirst.spring.demo.dto.PredictionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import codefirst.spring.demo.util.Constans;
import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Service
public class WekaService {
    public Instances dataSet;
    public Instances testDataset;
    MultilayerPerceptron mlp = new MultilayerPerceptron();
    static Logger logger = LoggerFactory.getLogger(WekaService.class);
    @Autowired
    ArffCreator arffCreator;

    @PostConstruct
    public void init() throws Exception {
        arffCreator.calculateArffDataSize();
        arffCreator.createFile();
        arffCreator.writeFile();
        loadDataSet();
        buildMlp();
    }

    public void loadDataSet() throws Exception {
        DataSource source = new DataSource(Constans.FILE_PATH_ARFF);
        dataSet = source.getDataSet();
        dataSet.setClassIndex(dataSet.numAttributes() - 1);

    }

    public void loadTestDataSet() throws Exception {
        DataSource testSource = new DataSource(Constans.FILE_PATH_TESTARFF);
        testDataset = testSource.getDataSet();
        testDataset.setClassIndex(testDataset.numAttributes() - 1);
    }

    public void buildMlp() {
        try {
            if (getModel() == null) {
                File fileModelArff = new File(Constans.MODELFILE_PATH_ARFF);
                fileModelArff.createNewFile();
                FileReader trainReader = new FileReader(Constans.FILE_PATH_ARFF);
                Instances train = new Instances(trainReader);
                train.setClassIndex(train.numAttributes() - 1);
                mlp.setLearningRate(0.087);
                mlp.setMomentum(0.53);
                mlp.setTrainingTime(1500);
                mlp.setHiddenLayers("7");
                mlp.buildClassifier(train);
                weka.core.SerializationHelper.write(Constans.MODELFILE_PATH_ARFF, mlp);
            } else {
                mlp = (MultilayerPerceptron) getModel();
            }

        } catch (Exception ex) {
            logger.info(ex.getMessage());

        }
    }

    public void testMlpPredictQuality() throws Exception {
        Instances testMywine = new Instances(new BufferedReader(new FileReader(Constans.FILE_PATH_TESTARFF)));
        testMywine.setClassIndex(testMywine.numAttributes() - 1);
        Instance testQuality;
        Instances predicttedData = new Instances(testMywine);
        double tolerance = 0;
        for (int i = 0; i < testMywine.numInstances(); i++) {
            double clsLabel = mlp.classifyInstance(testMywine.instance(i));
            predicttedData.instance(i).setClassValue(clsLabel);
            testQuality = testDataset.instance(i);
            tolerance += Math.abs(testQuality.classValue() - predicttedData.instance(i).classValue());
        }
        double deviationRate = (tolerance / testDataset.numInstances()) * 10;
        logger.info("Deviation Rate = " + deviationRate);

    }

    public double predictQuality(PredictionDTO predictionDTO) throws Exception {
        Instance instance = new DenseInstance(12);
        instance.setValue(dataSet.attribute(0), predictionDTO.getFixedAcidity());
        instance.setValue(dataSet.attribute(1), predictionDTO.getVolatileAcidity());
        instance.setValue(dataSet.attribute(2), predictionDTO.getCitricAcid());
        instance.setValue(dataSet.attribute(3), predictionDTO.getResidualSugar());
        instance.setValue(dataSet.attribute(4), predictionDTO.getChlorides());
        instance.setValue(dataSet.attribute(5), predictionDTO.getFreeSulfurDioxide());
        instance.setValue(dataSet.attribute(6), predictionDTO.getTotalSulfurDioxide());
        instance.setValue(dataSet.attribute(7), predictionDTO.getDensity());
        instance.setValue(dataSet.attribute(8), predictionDTO.getPH());
        instance.setValue(dataSet.attribute(9), predictionDTO.getSulphates());
        instance.setValue(dataSet.attribute(10), predictionDTO.getAlcohol());
        return mlp.classifyInstance(instance);
    }

    public Classifier getModel() throws Exception {
        File f = new File(Constans.MODELFILE_PATH_ARFF);
        if (f.exists()) {
            logger.info("Model file found ");
            return (Classifier) weka.core.SerializationHelper.read(Constans.MODELFILE_PATH_ARFF);
        }
        return null;
    }

}
