package utils;

public class List {
	private Element first;
	private Element last;
	
    private int size;
    
    public List(){
        this.size = 0;
    }

    public Element getFirst() {
        return first;
    }

    public void setFirst(Element first) {
        this.first = first;
    }

    public Element getLast() {
        return last;
    }

    public void setLast(Element last) {
        this.last = last;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public void add(String newValue){
        Element newElement = new Element(newValue);
        if (this.first == null && this.last == null){
            this.first = newElement;
            this.last = newElement;            
        }else{
            this.last.setNextValue(newElement);
            this.last = newElement;            
        }
        this.size++;
    }
    
    public void removeByValue(String valueSearched){
        Element prev = null;
        Element current = this.first;
        
        for(int i=0; i < this.getSize(); i++){            
            if (current.getValue().equalsIgnoreCase(valueSearched)){
                if (this.size == 1){
                    this.first = null;
                    this.last = null;
                }else if (current == first){
                    this.first = current.getNextValue();
                    current.setNextValue(null);
                }else if (current == last){
                    this.last = prev;
                    prev.setNextValue(null);
                }else{
                    prev.setNextValue(current.getNextValue());
                    current = null;
                }
                this.size--;
                break;
            }
            prev = current;
            current = current.getNextValue();
        }
    }
    
    public void removeByIndex(int index) {
    	Element prev = null;
    	Element current = this.first;
    	
    	for(int i = 0; i < this.getSize(); i++) {
    		if(index == i) {
    			if(this.size == 1) {
    				this.first = null;
    				this.last = null;
    			}
    			else if (current == first){
    				this.first = current.getNextValue();
    				current.setNextValue(null);
    			}
    			else if (current == last){
                    this.last = prev;
                    prev.setNextValue(null);
                }else{
                    prev.setNextValue(current.getNextValue());
                    current = null;
                }
                this.size--;
                break;
    		}
    		prev = current;
    		current = current.getNextValue();
    	}
    }
    
    public Element get(int position){
        Element current = this.first;
        for(int i=0; i < position; i++){
            if (current.getNextValue() != null){
                current = current.getNextValue();
            }
        }
        return current;
    }
    
    public String convertListInString() {
    	String value = "";
    	
    	for(int i = 0; i < this.getSize(); i++){
    		value += this.get(i).getValue() + " ";
    	}
    	
    	return value;
    }
}