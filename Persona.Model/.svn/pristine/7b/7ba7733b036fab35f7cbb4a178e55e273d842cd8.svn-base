package com.artica.telesalud.persona.model.impl;

public class PrivilegeId  implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int resource;
    private int operation;

   public PrivilegeId() {
   }

   public PrivilegeId(int resource, int operation) {
      this.resource = resource;
      this.operation = operation;
   }
  
   public int getResource() {
       return this.resource;
   }
   
   public void setResource(int resource) {
       this.resource = resource;
   }
   public int getOperation() {
       return this.operation;
   }
   
   public void setOperation(int operation) {
       this.operation = operation;
   }


  public boolean equals(Object other) {
        if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PrivilegeId) ) return false;
		 PrivilegeId castOther = ( PrivilegeId ) other; 
        
		 return (this.getResource()==castOther.getResource())
&& (this.getOperation()==castOther.getOperation());
  }
  
  public int hashCode() {
        int result = 17;
        
        result = 37 * result + this.getResource();
        result = 37 * result + this.getOperation();
        return result;
  }   


}
