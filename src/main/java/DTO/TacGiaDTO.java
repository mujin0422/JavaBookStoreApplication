package DTO;

import java.util.Objects;

public class TacGiaDTO {
    private int maTG;
    private String tenTG;

    public TacGiaDTO() {
    }

    public TacGiaDTO(int maTG, String tenTG) {
        this.maTG = maTG;
        this.tenTG = tenTG;
    }

    public int getMaTG() {
        return maTG;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setMaTG(int maTG) {
        this.maTG = maTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    @Override
    public String toString() {
        return "TacGiaDTO{" + "maTG=" + maTG + ", tenTG=" + tenTG + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.maTG;
        hash = 97 * hash + Objects.hashCode(this.tenTG);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TacGiaDTO other = (TacGiaDTO) obj;
        if (this.maTG != other.maTG) {
            return false;
        }
        return Objects.equals(this.tenTG, other.tenTG);
    }
    
    
}
