package sales.management.kiwamirembe.co.ug;

public class Unit {

    public int unit_id;
    public String unit_code;
    public String unit_name;
    public int unit_sync_status;

    public Unit() {
    }

    public Unit(int unit_id, String unit_code, String unit_name, int unit_sync_status) {
        this.unit_id = unit_id;
        this.unit_code = unit_code;
        this.unit_name = unit_name;
        this.unit_sync_status = unit_sync_status;
    }

    public Unit(String unit_code, String unit_name, int unit_sync_status) {
        this.unit_code = unit_code;
        this.unit_name = unit_name;
        this.unit_sync_status = unit_sync_status;
    }

    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public String getUnit_code() {
        return unit_code;
    }

    public void setUnit_code(String unit_code) {
        this.unit_code = unit_code;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public int getUnit_sync_status() {
        return unit_sync_status;
    }

    public void setUnit_sync_status(int unit_sync_status) {
        this.unit_sync_status = unit_sync_status;
    }
}
