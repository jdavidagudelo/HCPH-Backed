package com.artica.telesalud.persona.model.impl;

public class PermissionId  implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int role;
    private int resource;
    private int operation;

   public PermissionId() {
   }

   public PermissionId(int role, int resource, int operation) {
      this.role = role;
      this.resource = resource;
      this.operation = operation;
   }
  
   public int getRole() {
       return this.role;
   }
   
   public void setRole(int role) {
       this.role = role;
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
		 if ( !(other instanceof PermissionId) ) return false;
		 PermissionId castOther = ( PermissionId ) other; 
        
		 return (this.getRole()==castOther.getRole())
&& (this.getResource()==castOther.getResource())
&& (this.getOperation()==castOther.getOperation());
  }
  
  public int hashCode() {
        int result = 17;
        
        result = 37 * result + this.getRole();
        result = 37 * result + this.getResource();
        result = 37 * result + this.getOperation();
        return result;
  }   


}
