import java.util.ArrayList;
import java.util.List;

class MicroUSBCableAdapter implements IUniversalCharger {
    private MicroUSBCable microUSBCable;
    private String chargingMode;
    private String voltage;
    private List<ChargingEventHandler> eventHandlers;

    MicroUSBCableAdapter(MicroUSBCable microUSBCable) {
        this.microUSBCable = microUSBCable;
        this.eventHandlers = new ArrayList<>();
    }

    public void configure(String chargingMode, String voltage) {
        this.chargingMode = chargingMode;
        this.voltage = voltage;
    }

    @Override
    public void charge() {
        notifyChargeStart();

        microUSBCable.connectWithMicroUSBCable();
        System.out.println("Charging in " + chargingMode + " mode with " + voltage + " voltage.");

        notifyChargeComplete();
    }

    @Override
    public void addEventHandler(ChargingEventHandler handler) {
        eventHandlers.add(handler);
    }

    @Override
    public void removeEventHandler(ChargingEventHandler handler) {
        eventHandlers.remove(handler);
    }

    @Override
    public String getAdapterType() {
        return "Micro USB";
    }

    private void notifyChargeStart() {
        for (ChargingEventHandler handler : eventHandlers) {
            handler.onChargeStart();
        }
    }

    private void notifyChargeComplete() {
        for (ChargingEventHandler handler : eventHandlers) {
            handler.onChargeComplete();
        }
    }
}