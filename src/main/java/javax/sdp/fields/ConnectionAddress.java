/*
 * Conditions Of Use
 *
 *  This software was developed by employees of the National Institute of
 *  Standards and Technology (NIST), an agency of the Federal Government.
 *  Pursuant to title 15 Untied States Code Section 105, works of NIST
 *  employees are not subject to copyright protection in the United States
 *  and are considered to be in the public domain.  As a result, a formal
 *  license is not needed to use the software.
 *
 *  This software is provided by NIST as a service and is expressly
 *  provided "AS IS."  NIST MAKES NO WARRANTY OF ANY KIND, EXPRESS, IMPLIED
 *  OR STATUTORY, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTY OF
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, NON-INFRINGEMENT
 *  AND DATA ACCURACY.  NIST does not warrant or make any representations
 *  regarding the use of the software or the results thereof, including but
 *  not limited to the correctness, accuracy, reliability or usefulness of
 *  the software.
 *
 *  Permission to use this software is contingent upon your acceptance
 *  of the terms of this agreement
 */
/*******************************************************************************
 * Product of NIST/ITL Advanced Networking Technologies Division (ANTD).        *
 *******************************************************************************/
package javax.sdp.fields;

import gov.nist.core.Host;
import gov.nist.core.Separators;

/**
 * Connection Address of the SDP header (appears as part of the Connection field)
 *
 * @author Olivier Deruelle <deruelle@antd.gov.nist.gov>
 * @author M. Ranganathan   <br/>
 * @version JSR141-PUBLIC-REVIEW (subject to change)
 */
public class ConnectionAddress extends SDPObject {
    protected Host address;
    protected int ttl;
    protected int port;

    public Host getAddress() {
        return address;
    }

    public int getTtl() {
        return ttl;
    }

    public int getPort() {
        return port;
    }

    /**
     * Set the address member
     *
     * @param a
     */
    public void setAddress(Host a) {
        address = a;
    }

    /**
     * Set the ttl member
     *
     * @param ttl
     */
    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    /**
     * Set the port member
     *
     * @param p
     */
    public void setPort(int p) {
        port = p;
    }

    /**
     * Get the string encoded version of this object
     *
     * @since v1.0
     */
    public String encode() {
        String encoded_string = "";

        if (address != null) {
            encoded_string = address.encode();
        }
        if (ttl != 0 && port != 0) {
            encoded_string += Separators.SLASH + ttl + Separators.SLASH + port;
        } else if (ttl != 0) {
            encoded_string += Separators.SLASH + ttl;
        }
        return encoded_string;
    }

    public Object clone() {
        ConnectionAddress retval = (ConnectionAddress) super.clone();
        if (this.address != null) {
            retval.address = (Host) this.address.clone();
        }
        return retval;
    }

}
