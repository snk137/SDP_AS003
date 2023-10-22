import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<IUniversalCharger> chargers = new ArrayList<>();

        USBCCable usbCable = new USBCCable();
        USBCCableAdapter usbCableAdapter = new USBCCableAdapter(usbCable);
        usbCableAdapter.configure("Fast Charging", "5V");

        MicroUSBCable microUSBCable = new MicroUSBCable();
        MicroUSBCableAdapter microUSBCableAdapter = new MicroUSBCableAdapter(microUSBCable);
        microUSBCableAdapter.configure("Standard Charging", "4.5V");

        LightningCable lightningCable = new LightningCable();
        LightningCableAdapter lightningCableAdapter = new LightningCableAdapter(lightningCable);
        lightningCableAdapter.configure("Fast Charging", "6V");


        chargers.add(usbCableAdapter);
        chargers.add(microUSBCableAdapter);
        chargers.add(lightningCableAdapter);

        for (IUniversalCharger charger : chargers) {
            charger.addEventHandler(new ChargingEventHandler() {
                @Override
                public void onChargeStart() {
                    System.out.println("Charging started with " + charger.getAdapterType() + " adapter.");
                }

                @Override
                public void onChargeComplete() {
                    System.out.println("Charging completed with " + charger.getAdapterType() + " adapter.");
                }
            });
        }

        for (IUniversalCharger charger : chargers) {
            charger.charge();
        }
    }
}