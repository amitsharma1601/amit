package Models;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentDetails implements Parcelable {
    private String studentName;
    private Integer studentClass;
    private String rollNumber;

    public static final Parcelable.Creator<StudentDetails> CREATOR = new Creator<StudentDetails>() {
        @Override
        public StudentDetails createFromParcel(Parcel source) {
            return new StudentDetails(source);
        }

        @Override
        public StudentDetails[] newArray(int size) {
            return new StudentDetails[0];
        }
    };

    public StudentDetails(Parcel parcel){
        this.studentName = parcel.readString();
        this.studentClass = parcel.readInt();
        this.rollNumber = parcel.readString();
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public StudentDetails(String studentName, Integer studentClass, String rollNumber) {
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.rollNumber = rollNumber;
    }
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(Integer studentClass) {
        this.studentClass = studentClass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.studentName);
        dest.writeInt(this.studentClass);
        dest.writeString(this.rollNumber);
    }

    @Override
    public String toString() {
        return "StudentDetails{" +
                "studentName='" + studentName + '\'' +
                ", studentClass=" + studentClass +
                ", rollNumber='" + rollNumber + '\'' +
                '}';
    }
}
