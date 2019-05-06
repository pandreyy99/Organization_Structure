package salary;

import java.util.Objects;

public class Salary {
    private double salariuBrut;
    private double commissionPct = 0.0;
    private double salariuNet;

    public Salary() {
    }

    public Salary(double salariuBrut, double commissionPct) {
        this.salariuBrut = salariuBrut;
        this.commissionPct = commissionPct;
        if (commissionPct != 0.0)
            this.salariuNet = (0.6) * salariuBrut * (1 + commissionPct);
        else this.salariuNet = (0.6) * salariuBrut;
    }

    public double getSalariuBrut() {
        return salariuBrut;
    }

    public void setSalariuBrut(double salariuBrut) {
        this.salariuBrut = salariuBrut;
        if (commissionPct != 0.0)
            this.salariuNet = (0.6) * salariuBrut * (1 + commissionPct);
        else this.salariuNet = (0.6) * salariuBrut;
    }

    public double getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(float commissionPct) {
        this.commissionPct = commissionPct;
    }

    public double getSalariuNet() {
        return salariuNet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Salary)) return false;
        Salary salary = (Salary) o;
        return Double.compare(salary.salariuBrut, salariuBrut) == 0 &&
                Double.compare(salary.commissionPct, commissionPct) == 0 &&
                Double.compare(salary.salariuNet, salariuNet) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(salariuBrut, commissionPct, salariuNet);
    }

    @Override
    public String toString() {
        return "salary{" +
                "salariuBrut=" + salariuBrut +
                ", commissionPct=" + commissionPct +
                ", salariuNet=" + salariuNet +
                '}';
    }
}
