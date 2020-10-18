package codefirst.spring.demo.service;

import codefirst.spring.demo.entity.Prediction;
import codefirst.spring.demo.repository.PredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class PredictionService {
    @Autowired
    PredictionRepository predictionRepository;

    public void save(Prediction prediction) {
        if ((prediction.getAlcohol() >= 1 && prediction.getAlcohol() <= 10) && (prediction.getChlorides() >= 1 && prediction.getChlorides() <= 10) && (prediction.getCitricAcid() >= 1 && prediction.getCitricAcid() <= 10) && (prediction.getDensity() >= 1 && prediction.getDensity() <= 10) && (prediction.getFixedAcidity() >= 1 && prediction.getFixedAcidity() <= 10) && (prediction.getFreeSulfurDioxide() >= 1 && prediction.getFreeSulfurDioxide() <= 10) && (prediction.getPH() >= 1 && prediction.getPH() <= 10) && (prediction.getResidualSugar() >= 1 && prediction.getResidualSugar() <= 10) && (prediction.getSulphates() >= 1 && prediction.getSulphates() <= 10) && (prediction.getTotalSulfurDioxide() >= 1 && prediction.getTotalSulfurDioxide() <= 10) && (prediction.getVolatileAcidity() >= 1 && prediction.getVolatileAcidity() <= 10)) {
            predictionRepository.save(prediction);
        } else {
            throw new RuntimeException("Kayıtların her biri 1 ve 10 arası olmalıdır");
        }
    }

    public List<Prediction> findAll() {
        if (predictionRepository.count() >= 1) {
            return predictionRepository.findAll();
        } else {
            throw new RuntimeException("Herhangi bir kayıt bulunamadı");
        }
    }
}