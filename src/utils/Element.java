package utils;

public class Element {
    private String value;
    private Element nextValue;
    
    public Element(String newValue){
        this.value = newValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Element getNextValue() {
        return nextValue;
    }

    public void setNextValue(Element nextValue) {
        this.nextValue = nextValue;
    }
}