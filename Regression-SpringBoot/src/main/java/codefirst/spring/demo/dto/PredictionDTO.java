package codefirst.spring.demo.dto;

import lombok.Data;

@Data
public class PredictionDTO {
    private double fixedAcidity;
    private double volatileAcidity;
    private double citricAcid;
    private double residualSugar;
    private double chlorides;
    private double freeSulfurDioxide;
    private double totalSulfurDioxide;
    private double density;
    private double pH;
    private double sulphates;
    private double alcohol;
}
