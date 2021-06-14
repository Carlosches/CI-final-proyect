package co.edu.icesi.ci.tallerfinal.front.model.classes;

import javax.persistence.Column;

public class CheckMeasurPK {

    private long phychePhycheId;

    private long measMeasId;

    public CheckMeasurPK() {
    }
    public long getPhychePhycheId() {
        return this.phychePhycheId;
    }
    public void setPhychePhycheId(long phychePhycheId) {
        this.phychePhycheId = phychePhycheId;
    }
    public long getMeasMeasId() {
        return this.measMeasId;
    }
    public void setMeasMeasId(long measMeasId) {
        this.measMeasId = measMeasId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CheckMeasurPK)) {
            return false;
        }
        CheckMeasurPK castOther = (CheckMeasurPK)other;
        return
                (this.phychePhycheId == castOther.phychePhycheId)
                        && (this.measMeasId == castOther.measMeasId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + ((int) (this.phychePhycheId ^ (this.phychePhycheId >>> 32)));
        hash = hash * prime + ((int) (this.measMeasId ^ (this.measMeasId >>> 32)));

        return hash;
    }

}
