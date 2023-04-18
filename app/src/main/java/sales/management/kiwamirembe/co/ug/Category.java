package sales.management.kiwamirembe.co.ug;

public class Category {

    public int category_id;
    public String category_code;
    public String category_name;
    public String category_type;
    public int category_syc_status;

    public Category() {
    }

    public Category(int category_id, String category_code, String category_name, String category_type, int category_syc_status) {
        this.category_id = category_id;
        this.category_code = category_code;
        this.category_name = category_name;
        this.category_type = category_type;
        this.category_syc_status = category_syc_status;
    }

    public Category(String category_code, String category_name, String category_type, int category_syc_status) {
        this.category_code = category_code;
        this.category_name = category_name;
        this.category_type = category_type;
        this.category_syc_status = category_syc_status;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }

    public int getCategory_syc_status() {
        return category_syc_status;
    }

    public void setCategory_syc_status(int category_syc_status) {
        this.category_syc_status = category_syc_status;
    }
}
