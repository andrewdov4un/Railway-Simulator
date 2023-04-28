public abstract class FreightRailroadCars {
    private static int nextId = 1;
    private int id = nextId;
    private String shipper;
    private int netWeight;
    private int grossWeight;
    private final int maxWeight = 20000;

    public FreightRailroadCars(String shipper, int netWeight, int grossWeight) {
        id = nextId;
        nextId++;
        this.shipper = shipper;
        this.netWeight = netWeight;
        this.grossWeight = grossWeight;
    }
    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public int getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(int netWeight) {
        this.netWeight = netWeight;
    }

    public int getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(int grossWeight) {
        this.grossWeight = grossWeight;
    }


    public int getId() {
        return id;
    }
}
class HeavyRailroadFreightCar extends FreightRailroadCars {
    private final int id = getId();
    private final boolean improvedBrakingSystem;
    private final int loadCapacity;
    public HeavyRailroadFreightCar(String shipper, int netWeight, int grossWeight, boolean improvedBrakingSystem, int loadCapacity) {
        super(shipper, netWeight, grossWeight);
        this.improvedBrakingSystem = improvedBrakingSystem;
        this.loadCapacity = loadCapacity;
    }

    @Override
    public String toString() {
        return "\n --------------------" + "\n ID: " + id + "\n Shipper: " + getShipper() + "\n Net weight: " + getNetWeight() + "\n --------------------";
    }
    @Override
    public int getId() {
        return id;
    }

    public boolean isImprovedBrakingSystem() {
        return improvedBrakingSystem;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }
}

class RefrigeratedRailroadCar extends FreightRailroadCars {
    private final int id = getId();
    private final int maxTemperatureAllowed;
    private final boolean humidityControlSystem;
    public RefrigeratedRailroadCar(String shipper, int netWeight, int grossWeight, int maxTemperatureAllowed, boolean humidityControlSystem) {
        super(shipper, netWeight, grossWeight);
        this.maxTemperatureAllowed = maxTemperatureAllowed;
        this.humidityControlSystem = humidityControlSystem;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getMaxTemperatureAllowed() {
        return maxTemperatureAllowed;
    }

    public boolean isHumidityControlSystem() {
        return humidityControlSystem;
    }
}

class RailroadCarForLiquidMaterials extends FreightRailroadCars {
    private final int id = getId();
    private String typeOfMaterialTransported;
    private final int volumeCapacity;
    public RailroadCarForLiquidMaterials(String shipper, int netWeight, int grossWeight, String typeOfMaterialTransported, int volumeCapacity) {
        super(shipper, netWeight, grossWeight);
        this.typeOfMaterialTransported = typeOfMaterialTransported;
        this.volumeCapacity = volumeCapacity;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getTypeOfMaterialTransported() {
        return typeOfMaterialTransported;
    }

    public void setTypeOfMaterialTransported(String typeOfMaterialTransported) {
        this.typeOfMaterialTransported = typeOfMaterialTransported;
    }

    public int getVolumeCapacity() {
        return volumeCapacity;
    }
}

class RailroadCarForGaseousMaterials extends FreightRailroadCars {
    private final int id = getId();
    private int pressurePSI;
    private String typeOfGasTransported;
    public RailroadCarForGaseousMaterials(String shipper, int netWeight, int grossWeight, int pressurePSI, String typeOfGasTransported) {
        super(shipper, netWeight, grossWeight);
        this.pressurePSI = pressurePSI;
        this.typeOfGasTransported = typeOfGasTransported;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getPressurePSI() {
        return pressurePSI;
    }

    public void setPressurePSI(int pressurePSI) {
        this.pressurePSI = pressurePSI;
    }

    public String getTypeOfGasTransported() {
        return typeOfGasTransported;
    }

    public void setTypeOfGasTransported(String typeOfGasTransported) {
        this.typeOfGasTransported = typeOfGasTransported;
    }
}

class RailroadCarForExplosives extends HeavyRailroadFreightCar {
    private final int id = getId();
    private double hazardRating;
    private double blastResistanceRating;
    public RailroadCarForExplosives(String shipper, int netWeight, int grossWeight, boolean improvedBrakingSystem, int loadCapacity, double hazardRating, double blastResistanceRating) {
        super(shipper, netWeight, grossWeight, improvedBrakingSystem, loadCapacity);
        this.hazardRating = hazardRating;
        this.blastResistanceRating = blastResistanceRating;
    }

    @Override
    public int getId() {
        return id;
    }

    public double getHazardRating() {
        return hazardRating;
    }

    public void setHazardRating(double hazardRating) {
        this.hazardRating = hazardRating;
    }

    public double getBlastResistanceRating() {
        return blastResistanceRating;
    }

    public void setBlastResistanceRating(double blastResistanceRating) {
        this.blastResistanceRating = blastResistanceRating;
    }
}

class RailroadCarForToxicMaterials extends HeavyRailroadFreightCar {
    private final int id = getId();
    private String toxicityLevel;
    private final String ventilationSystemType;
    public RailroadCarForToxicMaterials(String shipper, int netWeight, int grossWeight, boolean improvedBrakingSystem, int loadCapacity, String toxicityLevel, String ventilationSystemType) {
        super(shipper, netWeight, grossWeight, improvedBrakingSystem, loadCapacity);
        this.toxicityLevel = toxicityLevel;
        this.ventilationSystemType = ventilationSystemType;
    }
    @Override
    public int getId() {
        return id;
    }

    public String getToxicityLevel() {
        return toxicityLevel;
    }

    public void setToxicityLevel(String toxicityLevel) {
        this.toxicityLevel = toxicityLevel;
    }

    public String getVentilationSystemType() {
        return ventilationSystemType;
    }
}

class RailroadCarForLiquidToxicMaterial extends HeavyRailroadFreightCar {
    private final int id = getId();
    public RailroadCarForLiquidToxicMaterial(String shipper, int netWeight, int grossWeight, boolean improvedBrakingSystem, int loadCapacity) {
        super(shipper, netWeight, grossWeight, improvedBrakingSystem, loadCapacity);
    }
}