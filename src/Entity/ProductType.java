package Entity;

public class ProductType {
	public String CodeType, TypeName, Size;
	
	public ProductType() {
		super();
	}

	public ProductType(String codeType, String typeName, String size) {
		super();
		CodeType = codeType;
		TypeName = typeName;
		Size = size;
	}

	public String getCodeType() {
		return CodeType;
	}

	public void setCodeType(String codeType) {
		CodeType = codeType;
	}

	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}
	 
}
