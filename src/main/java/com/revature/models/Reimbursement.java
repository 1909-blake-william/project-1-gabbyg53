package com.revature.models;

import java.sql.Timestamp;

public class Reimbursement {
	private int id;
	private double amount;
	private Timestamp dateSubmitted;
	private Timestamp resolveDate;
	private String description;
	private int author; //userId
	private String authorName;
	private int resolver; //userId
	private String resolverName;
	private int status; //statusId
	private String statusName;
	private int type; //typeId
	private String typeName;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

/*	public Reimbursement(int id, double amount, Timestamp dateSubmitted, Timestamp resolveDate, String description,
			int author, String authorName, int resolver, String resolverName, int status, String statusName, int type,
			String typeName) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateSubmitted = dateSubmitted;
		this.resolveDate = resolveDate;
		this.description = description;
		this.author = author;
		this.authorName = authorName;
		this.resolver = resolver;
		this.resolverName = resolverName;
		this.status = status;
		this.statusName = statusName;
		this.type = type;
		this.typeName = typeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Timestamp dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public Timestamp getResolveDate() {
		return resolveDate;
	}

	public void setResolveDate(Timestamp resolveDate) {
		this.resolveDate = resolveDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public String getResolverName() {
		return resolverName;
	}

	public void setResolverName(String resolverName) {
		this.resolverName = resolverName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + author;
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + ((dateSubmitted == null) ? 0 : dateSubmitted.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((resolveDate == null) ? 0 : resolveDate.hashCode());
		result = prime * result + resolver;
		result = prime * result + ((resolverName == null) ? 0 : resolverName.hashCode());
		result = prime * result + status;
		result = prime * result + ((statusName == null) ? 0 : statusName.hashCode());
		result = prime * result + type;
		result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (author != other.author)
			return false;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		if (dateSubmitted == null) {
			if (other.dateSubmitted != null)
				return false;
		} else if (!dateSubmitted.equals(other.dateSubmitted))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (resolveDate == null) {
			if (other.resolveDate != null)
				return false;
		} else if (!resolveDate.equals(other.resolveDate))
			return false;
		if (resolver != other.resolver)
			return false;
		if (resolverName == null) {
			if (other.resolverName != null)
				return false;
		} else if (!resolverName.equals(other.resolverName))
			return false;
		if (status != other.status)
			return false;
		if (statusName == null) {
			if (other.statusName != null)
				return false;
		} else if (!statusName.equals(other.statusName))
			return false;
		if (type != other.type)
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", "
				+ (dateSubmitted != null ? "dateSubmitted=" + dateSubmitted + ", " : "")
				+ (resolveDate != null ? "resolveDate=" + resolveDate + ", " : "")
				+ (description != null ? "description=" + description + ", " : "") + "author=" + author + ", "
				+ (authorName != null ? "authorName=" + authorName + ", " : "") + "resolver=" + resolver + ", "
				+ (resolverName != null ? "resolverName=" + resolverName + ", " : "") + "status=" + status + ", "
				+ (statusName != null ? "statusName=" + statusName + ", " : "") + "type=" + type + ", "
				+ (typeName != null ? "typeName=" + typeName : "") + "]";
	}*/

	public Reimbursement(int id, double amount, Timestamp dateSubmitted, Timestamp resolveDate, String description,
			int author, int resolver, int status, int type) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateSubmitted = dateSubmitted;
		this.resolveDate = resolveDate;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Timestamp dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public Timestamp getResolveDate() {
		return resolveDate;
	}

	public void setResolveDate(Timestamp resolveDate) {
		this.resolveDate = resolveDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + author;
		result = prime * result + ((dateSubmitted == null) ? 0 : dateSubmitted.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((resolveDate == null) ? 0 : resolveDate.hashCode());
		result = prime * result + resolver;
		result = prime * result + status;
		result = prime * result + type;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (author != other.author)
			return false;
		if (dateSubmitted == null) {
			if (other.dateSubmitted != null)
				return false;
		} else if (!dateSubmitted.equals(other.dateSubmitted))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (resolveDate == null) {
			if (other.resolveDate != null)
				return false;
		} else if (!resolveDate.equals(other.resolveDate))
			return false;
		if (resolver != other.resolver)
			return false;
		if (status != other.status)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", "
				+ (dateSubmitted != null ? "dateSubmitted=" + dateSubmitted + ", " : "")
				+ (resolveDate != null ? "resolveDate=" + resolveDate + ", " : "")
				+ (description != null ? "description=" + description + ", " : "") + "author=" + author + ", resolver="
				+ resolver + ", status=" + status + ", type=" + type + "]";
	}
	
}
