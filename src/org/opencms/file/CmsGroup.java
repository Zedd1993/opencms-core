/*
 * File   : $Source: /alkacon/cvs/opencms/src/org/opencms/file/CmsGroup.java,v $
 * Date   : $Date: 2006/01/06 15:37:27 $
 * Version: $Revision: 1.16.2.2 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Mananagement System
 *
 * Copyright (c) 2005 Alkacon Software GmbH (http://www.alkacon.com)
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
 * For further information about Alkacon Software GmbH, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.file;

import org.opencms.main.CmsIllegalArgumentException;
import org.opencms.security.CmsPrincipal;
import org.opencms.security.I_CmsPrincipal;
import org.opencms.util.CmsStringUtil;
import org.opencms.util.CmsUUID;

import java.util.Iterator;
import java.util.List;

/**
 * A group principal in the OpenCms permission system.<p>
 *
 * @author Alexander Kandzior 
 * @author Michael Emmerich 
 * 
 * @version $Revision: 1.16.2.2 $
 * 
 * @since 6.0.0 
 * 
 * @see CmsUser
 */
public class CmsGroup extends CmsPrincipal implements I_CmsPrincipal {

    /** The parent id of the group. */
    private CmsUUID m_parentId;

    /**
     * Creates a new, empty OpenCms group principal.
     */
    public CmsGroup() {

        int todo = 0;
        // check different semantic between empty user / group constructors (variable initialization yes/no)
    }

    /**
     * Creates a new OpenCms group principal.
     * 
     * @param id the unique id of the group
     * @param parentId the is of the parent group
     * @param name the name of the group
     * @param description the description of the group
     * @param flags the flags of the group    
     */
    public CmsGroup(CmsUUID id, CmsUUID parentId, String name, String description, int flags) {

        m_id = id;
        m_name = name;
        m_description = description;
        m_flags = flags;
        m_parentId = parentId;
    }

    /**
     * Filters out all groups with flags greater than <code>{@link I_CmsPrincipal#FLAG_CORE_LIMIT}</code>.<p>
     * 
     * @param groups the list of <code>{@link CmsGroup}</code>
     * 
     * @return the filtered groups list
     */
    public static List filterCore(List groups) {

        // TODO: should this method really be here?
        // TODO: unify with user based on new Principal class
        int todo = 0;

        Iterator it = groups.iterator();
        while (it.hasNext()) {
            CmsGroup group = (CmsGroup)it.next();
            if (group.getFlags() > I_CmsPrincipal.FLAG_CORE_LIMIT) {
                it.remove();
            }
        }
        return groups;
    }

    /**
     * Filters out all groups that does not have the given flag set,
     * but leaving all groups with flags less than <code>{@link I_CmsPrincipal#FLAG_CORE_LIMIT}</code>.<p>
     * 
     * @param groups the list of <code>{@link CmsGroup}</code>
     * @param flag the flag for filtering
     * 
     * @return the filtered groups list
     */
    public static List filterCoreFlag(List groups, int flag) {

        // TODO: should this method really be here?
        // TODO: unify with user based on new Principal class
        int todo = 0;

        Iterator it = groups.iterator();
        while (it.hasNext()) {
            CmsGroup group = (CmsGroup)it.next();
            if (group.getFlags() > I_CmsPrincipal.FLAG_CORE_LIMIT && (group.getFlags() & flag) != flag) {
                it.remove();
            }
        }
        return groups;
    }

    /**
     * Filters out all groups that does not have the given flag set.<p>
     * 
     * @param groups the list of <code>{@link CmsGroup}</code>
     * @param flag the flag for filtering
     * 
     * @return the filtered groups list
     */
    public static List filterFlag(List groups, int flag) {

        // TODO: should this method really be here?
        // TODO: unify with user based on new Principal class
        int todo = 0;

        Iterator it = groups.iterator();
        while (it.hasNext()) {
            CmsGroup group = (CmsGroup)it.next();
            if ((group.getFlags() & flag) != flag) {
                it.remove();
            }
        }
        return groups;
    }

    /**
     * Checks if the provided group name is valid and can be used as an argument value 
     * for {@link #setName(String)}.<p> 
     * 
     * A group name must not be empty or whitespace only.<p>
     * 
     * @param name the group name to check
     */
    public void checkName(String name) {

        if (CmsStringUtil.isEmptyOrWhitespaceOnly(name)) {
            throw new CmsIllegalArgumentException(Messages.get().container(Messages.ERR_GROUPNAME_VALIDATION_0));
        }
    }

    /**
     * @see java.lang.Object#clone()
     */
    public Object clone() {

        return new CmsGroup(m_id, m_parentId, m_name, m_description, m_flags);
    }

    /**
     * Returns true if this group is disabled.<p>
     * 
     * @return true if this group is disabled
     * 
     * @deprecated use {@link CmsPrincipal#isEnabled()} instead
     */
    public boolean getDisabled() {

        return !isEnabled();
    }

    /**
     * Returns the parent group id of this group.<p>
     * 
     * @return the parent group id of this group
     */
    public CmsUUID getParentId() {

        return m_parentId;
    }

    /**
     * Returns <code>true</code> if this group is enabled as a project user group.<p> 
     * 
     * @return <code>true</code> if this group is enabled as a project user group 
     */
    public boolean getProjectCoWorker() {

        return (getFlags() & I_CmsPrincipal.FLAG_GROUP_PROJECT_USER) == I_CmsPrincipal.FLAG_GROUP_PROJECT_USER;
    }

    /**
     * Returns <code>true</code> if this group is enabled as a project manager group.<p> 
     * 
     * @return <code>true</code> if this group is enabled as a project manager group
     */
    public boolean getProjectManager() {

        return (getFlags() & I_CmsPrincipal.FLAG_GROUP_PROJECT_MANAGER) == I_CmsPrincipal.FLAG_GROUP_PROJECT_MANAGER;
    }

    /**
     * Returns <code>true</code> if this group is enabled as a role for tasks.<p>
     * 
     * @return <code>true</code> if this group is enabled as a role for tasks
     */
    public boolean getRole() {

        return (getFlags() & I_CmsPrincipal.FLAG_GROUP_WORKFLOW_ROLE) == I_CmsPrincipal.FLAG_GROUP_WORKFLOW_ROLE;
    }

    /**
     * @see org.opencms.security.I_CmsPrincipal#isGroup()
     */
    public boolean isGroup() {

        return true;
    }

    /**
     * @see org.opencms.security.I_CmsPrincipal#isUser()
     */
    public boolean isUser() {

        return false;
    }

    /**
     * Disables this group.<p>
     * 
     * @deprecated use {@link CmsPrincipal#setEnabled(boolean)} instead
     */
    public void setDisabled() {

        setEnabled(false);
    }

    /**
     * Enables this group.<p>
     * 
     * @deprecated use {@link CmsPrincipal#setEnabled(boolean)} instead
     */
    public void setEnabled() {

        setEnabled(true);
    }

    /**
     * Sets the parent group id of this group.<p>
     * 
     * @param parentId the parent group id to set
     */
    public void setParentId(CmsUUID parentId) {

        m_parentId = parentId;
    }

    /**
     * Sets the project user flag for this group to the given value.<p>
     * 
     * @param value the value to set
     */
    public void setProjectCoWorker(boolean value) {

        if (getProjectCoWorker() != value) {
            setFlags(getFlags() ^ I_CmsPrincipal.FLAG_GROUP_PROJECT_USER);
        }
    }

    /**
     * Sets the project manager flag for this group to the given value.<p>
     * 
     * @param value the value to set
     */
    public void setProjectManager(boolean value) {

        if (getProjectManager() != value) {
            setFlags(getFlags() ^ I_CmsPrincipal.FLAG_GROUP_PROJECT_MANAGER);
        }
    }

    /**
     * Sets the "role for tasks" flag for this group to the given value.<p>
     * 
     * @param value the value to set
     */
    public void setRole(boolean value) {

        if (getRole() != value) {
            setFlags(getFlags() ^ I_CmsPrincipal.FLAG_GROUP_WORKFLOW_ROLE);
        }
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {

        StringBuffer result = new StringBuffer();
        result.append("[Group]");
        result.append(" name:");
        result.append(m_name);
        result.append(" id:");
        result.append(m_id);
        result.append(" description:");
        result.append(m_description);
        return result.toString();
    }
}