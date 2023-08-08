package org.adam.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.Date;

public class TableObject {
    @JsonProperty("source_ts")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sourceTs;
    @JsonProperty("lsn")
    private BigInteger lsn;

    public Date getSourceTs() {
        return sourceTs;
    }

    public void setSourceTs(Date source_ts) {
        this.sourceTs = source_ts;
    }

    public BigInteger getLsn() {
        return lsn;
    }

    public void setLsn(BigInteger lsn) {
        this.lsn = lsn;
    }

}
