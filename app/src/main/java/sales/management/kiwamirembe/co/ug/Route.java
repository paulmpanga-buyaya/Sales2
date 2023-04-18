package sales.management.kiwamirembe.co.ug;

public class Route {

    public int routeID;
    public String truckID;
    public String routeName;
    public int isSynced;

    public Route() {
    }

    public Route(int routeID, String truckID, String routeName, int isSynced) {
        this.routeID = routeID;
        this.truckID = truckID;
        this.routeName = routeName;
        this.isSynced = isSynced;
    }

    public Route(String truckID, String routeName, int isSynced) {
        this.truckID = truckID;
        this.routeName = routeName;
        this.isSynced = isSynced;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public String getTruckID() {
        return truckID;
    }

    public void setTruckID(String truckID) {
        this.truckID = truckID;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public int getIsSynced() {
        return isSynced;
    }

    public void setIsSynced(int isSynced) {
        this.isSynced = isSynced;
    }
}
