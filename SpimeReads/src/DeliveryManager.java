import java.util.Date;
public class DeliveryManager {
    private int[] agentIds = new int[]{1, 2, 3, 4};
    private  boolean isAgentAvailable[] = new boolean[]{true, true, true, true, true};
    private String[] agentNames = new String[]{"shaym", "gokul", "ashwin", "bharath", "vignesh"};
  static  int agentCount = 0;
    int currIndex = 0;

    void placeOrder(Order currentOrder) {
        String checkForAgent = assignAgent();
                if (!checkForAgent.equals("NO_AGENT_AVAILABLE") && currIndex < 4) {
                    currentOrder.deliveryAgentId = agentIds[currIndex];
                    currentOrder.orderStatus = "DELIVERED";
                    currentOrder.borrowDate = new Date();
                    Long addedDate = (new Date().getTime()) + (14 * 24 * 3600 * 1000);
                    currentOrder.dueDate = new Date(addedDate);

                } else {
                    currentOrder.orderStatus = "DELIVERY DELAYED";
                }
            }




    String assignAgent() {

        if (isAgentAvailable[agentCount] && agentCount < 4) {
            isAgentAvailable[agentCount] = false;
            currIndex = agentCount;

            agentCount++;
            return agentNames[currIndex];
        } else
            return "NO_AGENT_AVAILABLE";
    }
}
