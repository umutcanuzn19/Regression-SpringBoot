package codefirst.spring.demo.mapper;

import codefirst.spring.demo.dto.PredictionDTO;
import codefirst.spring.demo.dto.ResultDTO;
import codefirst.spring.demo.entity.Prediction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PredictionMapper {

    Prediction toPrediction(PredictionDTO predictionDTO);

    ResultDTO toResultDTO(Prediction prediction);

}
