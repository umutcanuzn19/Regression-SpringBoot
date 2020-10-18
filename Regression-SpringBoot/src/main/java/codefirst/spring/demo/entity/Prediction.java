package codefirst.spring.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "prediction")
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fixedAcidity")
    private double fixedAcidity;
    @Column(name = "volatileAcidity")
    private double volatileAcidity;
    @Column(name = "citricAcid")
    private double citricAcid;
    @Column(name = "residualSugar")
    private double residualSugar;
    @Column(name = "chlorides")
    private double chlorides;
    @Column(name = "freeSulfurDioxide")
    private double freeSulfurDioxide;
    @Column(name = "totalSulfurDioxide")
    private double totalSulfurDioxide;
    @Column(name = "density")
    private double density;
    @Column(name = "pH")
    private double pH;
    @Column(name = "sulphates")
    private double sulphates;
    @Column(name = "alcohol")
    private double alcohol;
    @Column(name = "quality")
    private double quality;

    public Prediction() {

    }

    public Prediction(double fixedAcidity, double volatileAcidity, double citricAcid, double residualSugar, double chlorides, double freeSulfurDioxide, double totalSulfurDioxide, double density, double pH, double sulphates, double alcohol, double quality) {
        this.fixedAcidity = fixedAcidity;
        this.volatileAcidity = volatileAcidity;
        this.citricAcid = citricAcid;
        this.residualSugar = residualSugar;
        this.chlorides = chlorides;
        this.freeSulfurDioxide = freeSulfurDioxide;
        this.totalSulfurDioxide = totalSulfurDioxide;
        this.density = density;
        this.pH = pH;
        this.sulphates = sulphates;
        this.alcohol = alcohol;
        this.quality = quality;
    }

}
