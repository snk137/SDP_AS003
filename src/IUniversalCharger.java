import java.util.List;

interface IUniversalCharger {
    void charge();
    void addEventHandler(ChargingEventHandler handler);
    void removeEventHandler(ChargingEventHandler handler);
    String getAdapterType();
}
