/*
 * File   : $Source: /alkacon/cvs/opencms/src/org/opencms/security/CmsPrincipal.java,v $
 * Date   : $Date: 2006/01/06 15:37:27 $
 * Version: $Revision: 1.1.2.1 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Mananagement System
 *
 * Copyright (C) 2002 - 2005 Alkacon Software (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.security;

import org.opencms.file.CmsGroup;
import org.opencms.file.CmsObject;
import org.opencms.file.CmsUser;
import org.opencms.main.CmsException;
import org.opencms.util.CmsStringUtil;
import org.opencms.util.CmsUUID;

/**
 * Common methods shared among user and group principals, 
 * also contains several utility functions to deal with principal instances.<p>
 * 
 * @author Alexander Kandzior
 * 
 * @version $Revision: 1.1.2.1 $ 
 * 
 * @since 6.2.0 
 */
public abstract class CmsPrincipal implements I_CmsPrincipal {

    /** The description of this principal. */
    protected String m_description;

    /** The flags of this principal. */
    protected int m_flags;

    /** The unique id of this principal. */
    protected CmsUUID m_id;

    /** The name of this principal. */
    protected String m_name;

    /**
     * Empty constructor for subclassing.<p>
     */
    protected CmsPrincipal() {

        // empty constructor for subclassing
    }

    /**
     * Returns the provided group name prefixed with <code>{@link I_CmsPrincipal#PRINCIPAL_GROUP}.</code>.<p>
     * 
     * @param name the name to add the prefix to
     * @return the provided group name prefixed with <code>{@link I_CmsPrincipal#PRINCIPAL_GROUP}.</code>
     */
    public static String getPrefixedGroup(String name) {

        StringBuffer result = new StringBuffer(name.length() + 10);
        result.append(I_CmsPrincipal.PRINCIPAL_GROUP);
        result.append('.');
        result.append(name);
        return result.toString();
    }

    /**
     * Returns the provided user name prefixed with <code>{@link I_CmsPrincipal#PRINCIPAL_USER}.</code>.<p>
     * 
     * @param name the name to add the prefix to
     * @return the provided user name prefixed with <code>{@link I_CmsPrincipal#PRINCIPAL_USER}.</code>
     */
    public static String getPrefixedUser(String name) {

        StringBuffer result = new StringBuffer(name.length() + 10);
        result.append(I_CmsPrincipal.PRINCIPAL_USER);
        result.append('.');
        result.append(name);
        return result.toString();
    }

    /**
     * Utility function to read a prefixed principal from the OpenCms database using the 
     * provided OpenCms user context.<p>
     * 
     * The principal must be either prefixed with <code>{@link I_CmsPrincipal#PRINCIPAL_GROUP}.</code> or
     * <code>{@link I_CmsPrincipal#PRINCIPAL_USER}.</code>.<p>
     * 
     * @param cms the OpenCms user context to use when reading the principal
     * @param name the prefixed principal name
     * 
     * @return the principal read from the OpenCms database
     * 
     * @throws CmsException in case the principal could not be read
     */
    public static I_CmsPrincipal readPrefixedPrincipal(CmsObject cms, String name) throws CmsException {

        if (CmsStringUtil.isNotEmpty(name)) {
            String upperCaseName = name.toUpperCase();
            if (upperCaseName.startsWith(I_CmsPrincipal.PRINCIPAL_GROUP)) {
                // this principal is a group
                String groupName = name.substring(I_CmsPrincipal.PRINCIPAL_GROUP.length() + 1);
                return cms.readGroup(groupName);
            } else if (upperCaseName.startsWith(I_CmsPrincipal.PRINCIPAL_USER)) {
                // this principal is a user
                String userName = name.substring(I_CmsPrincipal.PRINCIPAL_USER.length() + 1);
                return cms.readUser(userName);
            }
        }
        // invalid principal name was given
        throw new CmsSecurityException(Messages.get().container(Messages.ERR_INVALID_PRINCIPAL_1, name));
    }
    
    /**
     * Utility function to read a principal of the given type from the OpenCms database using the 
     * provided OpenCms user context.<p>
     * 
     * The type must either be <code>{@link I_CmsPrincipal#PRINCIPAL_GROUP}</code> or
     * <code>{@link I_CmsPrincipal#PRINCIPAL_USER}</code>.<p>
     * 
     * @param cms the OpenCms user context to use when reading the principal
     * @param type the principal type 
     * @param name the principal name
     * 
     * @return the principal read from the OpenCms database
     * 
     * @throws CmsException in case the principal could not be read
     */
    public static I_CmsPrincipal readPrincipal(CmsObject cms, String type, String name) throws CmsException {

        if (CmsStringUtil.isNotEmpty(type)) {
            String upperCaseType = type.toUpperCase();
            if (PRINCIPAL_GROUP.equals(upperCaseType)) {
                // this principal is a group
                return cms.readGroup(name);
            } else if (PRINCIPAL_USER.equals(upperCaseType)) {
                // this principal is a user
                return cms.readUser(name);
            }
        }
        // invalid principal type was given
        throw new CmsSecurityException(Messages.get().container(Messages.ERR_INVALID_PRINCIPAL_TYPE_2, type, name));
    }    

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (obj instanceof I_CmsPrincipal) {
            if (m_id != null) {
                return m_id.equals(((I_CmsPrincipal)obj).getId());
            }
        }
        return false;
    }

    /**
     * @see org.opencms.security.I_CmsPrincipal#getDescription()
     */
    public String getDescription() {

        return m_description;
    }

    /**
     * @see org.opencms.security.I_CmsPrincipal#getFlags()
     */
    public int getFlags() {

        return m_flags;
    }

    /**
     * @see org.opencms.security.I_CmsPrincipal#getId()
     */
    public CmsUUID getId() {

        return m_id;
    }

    /**
     * @see java.security.Principal#getName()
     */
    public String getName() {

        return m_name;
    }

    /**
     * @see org.opencms.security.I_CmsPrincipal#getPrefixedName()
     */
    public String getPrefixedName() {

        if (isUser()) {
            return getPrefixedUser(getName());
        } else if (isGroup()) {
            return getPrefixedGroup(getName());
        }
        return getName();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {

        if (m_id != null) {
            return m_id.hashCode();
        }
        return CmsUUID.getNullUUID().hashCode();
    }

    /**
     * @see org.opencms.security.I_CmsPrincipal#isEnabled()
     */
    public boolean isEnabled() {

        return (getFlags() & I_CmsPrincipal.FLAG_DISABLED) == 0;
    }

    /**
     * @see org.opencms.security.I_CmsPrincipal#isGroup()
     */
    public boolean isGroup() {

        return this instanceof CmsGroup;
    }

    /**
     * @see org.opencms.security.I_CmsPrincipal#isUser()
     */
    public boolean isUser() {

        return this instanceof CmsUser;
    }

    /**
     * @see org.opencms.security.I_CmsPrincipal#setDescription(java.lang.String)
     */
    public void setDescription(String description) {

        m_description = description;
    }

    /**
     * @see org.opencms.security.I_CmsPrincipal#setEnabled(boolean)
     */
    public void setEnabled(boolean enabled) {

        if (enabled != isEnabled()) {
            // toggle disabled flag if required
            setFlags(getFlags() ^ I_CmsPrincipal.FLAG_DISABLED);
        }
    }

    /**
     * @see org.opencms.security.I_CmsPrincipal#setFlags(int)
     */
    public void setFlags(int value) {

        m_flags = value;
    }

    /**
     * @see org.opencms.security.I_CmsPrincipal#setName(java.lang.String)
     */
    public void setName(String name) {

        checkName(name);
        m_name = name;
    }
}