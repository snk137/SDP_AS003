import java.util.ArrayList;
import java.util.List;
class USBCCableAdapter implements IUniversalCharger {
    private USBCCable usbCable;
    private String chargingMode;
    private String voltage;
    private List<ChargingEventHandler> eventHandlers;

    USBCCableAdapter(USBCCable usbCable) {
        this.usbCable = usbCable;
        this.eventHandlers = new ArrayList<>();
    }

    public void configure(String chargingMode, String voltage) {
        this.chargingMode = chargingMode;
        this.voltage = voltage;
    }

    @Override
    public void charge() {
        notifyChargeStart();

        usbCable.connectWithUSBCCable();
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
        return "USB-C";
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