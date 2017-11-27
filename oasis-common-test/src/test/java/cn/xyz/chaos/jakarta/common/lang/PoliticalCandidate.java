package cn.xyz.chaos.jakarta.common.lang;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用ToStringBuilder和CompareToBuilder分别实现toString()和compareTo()方法
 *
 * @author lvchenggang
 *
 */
public class PoliticalCandidate implements Comparable {

    private String lastName;
    private String firstName;
    private Date dateOfBirth;
    private BigDecimal moneyRaised;
    private State state;

    public PoliticalCandidate() {
    }

    public PoliticalCandidate(String lastName, String firstName, Date dateOfBirth, BigDecimal moneyRaised,
            State state) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.moneyRaised = moneyRaised;
        this.state = state;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BigDecimal getMoneyRaised() {
        return moneyRaised;
    }

    public void setMoneyRaised(BigDecimal moneyRaised) {
        this.moneyRaised = moneyRaised;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    /*
     * public String toString() { return ReflectionToStringBuilder.reflectionToString(this); }
     */

    /*
     * // get/set methods are omitted for brevity... public String toString() { return new ToStringBuilder(this,
     * ToStringStyle.MULTI_LINE_STYLE) .append( "lastName", lastName ) .append( "firstName", firstName ) .toString(); }
     */

    /*
     * // get/set methods are omitted for brevity... public String toString() { return new ToStringBuilder(this,
     * ToStringStyle.DEFAULT_STYLE) .append( "lastName", lastName ) .append( "firstName", firstName ) .toString(); }
     */

    /*
     * // get/set methods are omitted for brevity... public String toString() { return new ToStringBuilder(this,
     * ToStringStyle.NO_FIELD_NAMES_STYLE) .append( "lastName", lastName ) .append( "firstName", firstName )
     * .toString(); }
     */

    // get/set methods are omitted for brevity...
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("lastName", lastName)
                .append("firstName", firstName).toString();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    // A compare to which mimics the behavior of equals()
    public int compareTo(Object o) {
        int compare = -1; // By default return less-than
        if (o != null && PoliticalCandidate.class.isAssignableFrom(o.getClass())) {
            PoliticalCandidate pc = (PoliticalCandidate) o;
            compare = (new CompareToBuilder().append(firstName, pc.firstName).append(lastName, pc.lastName))
                    .toComparison();
        }
        return compare;
    }

    // A hashCode which creates a hash from the two unique identifiers
    @Override
    public int hashCode() {

        return new HashCodeBuilder(17, 37).append(firstName).append(lastName).toHashCode();

    }

    // An equals which compares two unique identifiers
    public boolean equals(Object o) {
        boolean equals = false;
        if (o != null && o instanceof PoliticalCandidate) {
            PoliticalCandidate pc = (PoliticalCandidate) o;
            equals = (new EqualsBuilder().append(firstName, pc.firstName).append(lastName, pc.lastName)).isEquals();
        }
        return equals;
    }

}
