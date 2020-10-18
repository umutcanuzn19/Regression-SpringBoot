package codefirst.spring.demo.controller;

import codefirst.spring.demo.dto.PredictionDTO;
import codefirst.spring.demo.dto.ResultDTO;
import codefirst.spring.demo.entity.Prediction;
import codefirst.spring.demo.mapper.PredictionMapper;
import codefirst.spring.demo.service.PredictionService;
import codefirst.spring.demo.service.WekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PredictionController {
    @Autowired
    WekaService wekaService;
    @Autowired
    PredictionService predictionService;
    @Autowired
    PredictionMapper predictionMapper;

    @GetMapping("/predict/all")
    public List<Prediction> getAllPredicts() {
        return predictionService.findAll();
    }

    @PostMapping(value = "/predict-quality", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResultDTO predictWineQuality(@RequestBody PredictionDTO predictionDTO) throws Exception {
        double score = wekaService.predictQuality(predictionDTO);
        Prediction prediction = predictionMapper.toPrediction(predictionDTO);
        prediction.setQuality(score);
        predictionService.save(prediction);
        return predictionMapper.toResultDTO(prediction);
    }
}
