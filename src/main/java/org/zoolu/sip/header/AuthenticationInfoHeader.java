/*
 * Copyright (C) 2005 Luca Veltri - University of Parma - Italy
 *
 *  This file is part of MjSip (http://www.mjsip.org)
 *
 *  MjSip is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  MjSip is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with MjSip; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *  Author(s):
 *  Luca Veltri (luca.veltri@unipr.it)
 *
 *  Modified:
 *  Benhur Langoni (bhlangonijr@gmail.com)
 *  Thiago Camargo (barata7@gmail.com)
 */

package org.zoolu.sip.header;


import org.zoolu.sip.provider.SipParser;

import java.util.ArrayList;
import java.util.List;


/**
 * SIP AuthenticationInfo header
 */
public class AuthenticationInfoHeader extends AuthenticationHeader {
    /**
     * Creates a new AuthenticationInfoHeader
     */
    public AuthenticationInfoHeader() {
        super(SipHeaders.AuthenticationInfo, "");
    }

    /**
     * Creates a new AuthenticationInfoHeader
     *
     * @param hvalue
     */
    public AuthenticationInfoHeader(String hvalue) {
        super(SipHeaders.AuthenticationInfo, hvalue);
    }

    /**
     * Creates a new AuthenticationInfoHeader
     *
     * @param hd
     */
    public AuthenticationInfoHeader(Header hd) {
        super(hd);
    }

    /**
     * Creates a new AuthenticationInfoHeader
     * specifing the <i>auth_scheme</i> and the List of authentication parameters.
     * <p> <i>authParam</i> is a List of String of the form <i>parm_name</i> "=" <i>parm_value</i>
     *
     * @param authParams
     */
    public AuthenticationInfoHeader(List authParams) {
        super(SipHeaders.AuthenticationInfo, "", authParams);
    }

    /**
     * Whether has parameter <i>param_name</i>
     */
    public boolean hasParameter(String param_name) {
        char[] name_separators = {'=', ' ', '\t', '\r', '\n'};
        SipParser par = new SipParser(value);
        //par.skipString(); // skip the auth_scheme
        par.skipWSPCRLF();
        while (par.hasMore()) {
            String name = par.getWord(name_separators);
            if (name.equals(param_name)) {
                return true;
            }
            par.goToCommaHeaderSeparator().skipChar().skipWSPCRLF();
        }
        return false;
    }

    /**
     * Returns the parameter <i>param_name</i>, in case removing quotes.
     */
    public String getParameter(String param_name) {
        char[] name_separators = {'=', ' ', '\t'};
        SipParser par = new SipParser(value);
        //par.skipString(); // skip the auth_scheme
        par.skipWSPCRLF();
        while (par.hasMore()) {
            String name = par.getWord(name_separators);
            if (name.equals(param_name)) {
                par.goTo('=').skipChar().skipWSP();
                int comma = par.indexOfCommaHeaderSeparator();
                if (comma >= 0) {
                    par = new SipParser(par.getString(comma - par.getPos()));
                }
                return par.getStringUnquoted();
            } else {
                par.goToCommaHeaderSeparator().skipChar().skipWSPCRLF();
            }
        }
        return null;
    }

    /**
     * Gets a String List of parameter names.
     *
     * @returns a List of String.
     */
    public List getParameters() {
        char[] name_separators = {'=', ' ', '\t'};
        SipParser par = new SipParser(value);
        //par.skipString(); // skip the auth_scheme
        par.skipWSPCRLF();
        List names = new ArrayList();
        while (par.hasMore()) {
            String name = par.getWord(name_separators);
            names.add(name);
            par.goToCommaHeaderSeparator().skipChar().skipWSPCRLF();
        }
        return names;
    }

    /**
     * Gets the athentication scheme. Note that for AuthenticationInfoHeader it always return null.
     */
    public String getAuthScheme() {
        return null;
    }
}