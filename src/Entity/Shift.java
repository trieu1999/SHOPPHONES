package Entity;

import java.sql.Date;

public class Shift {
	public String CodeShift, ShiftName;
    public Date TimeStart, TimeStop;
    public float Salary;
    
	public Shift() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Shift(String codeShift, String shiftName, Date timeStart, Date timeStop, float salary) {
		super();
		CodeShift = codeShift;
		ShiftName = shiftName;
		TimeStart = timeStart;
		TimeStop = timeStop;
		Salary = salary;
	}
	public String getCodeShift() {
		return CodeShift;
	}
	public void setCodeShift(String codeShift) {
		CodeShift = codeShift;
	}
	public String getShiftName() {
		return ShiftName;
	}
	public void setShiftName(String shiftName) {
		ShiftName = shiftName;
	}
	public Date getTimeStart() {
		return TimeStart;
	}
	public void setTimeStart(Date timeStart) {
		TimeStart = timeStart;
	}
	public Date getTimeStop() {
		return TimeStop;
	}
	public void setTimestop(Date timeStop) {
		TimeStop = timeStop;
	}
	public float getSalary() {
		return Salary;
	}
	public void setSalary(float salary) {
		Salary = salary;
	}
    
}
