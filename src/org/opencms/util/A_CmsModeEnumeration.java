/*
 * File   : $Source: /alkacon/cvs/opencms/src/org/opencms/util/Attic/A_CmsModeEnumeration.java,v $
 * Date   : $Date: 2006/11/14 14:34:21 $
 * Version: $Revision: 1.1.2.1 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Mananagement System
 *
 * Copyright (C) 2006 Alkacon Software GmbH (http://www.alkacon.com)
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

package org.opencms.util;

import java.io.Serializable;

/**
 * Base class for all mode enumeration classes.<p>
 *
 * Like:<br>
 * <ul>
 *   <li>{@link org.opencms.file.CmsResource.CmsResourceCopyMode}
 *   <li>{@link org.opencms.file.CmsResource.CmsResourceDeleteMode}
 *   <li>{@link org.opencms.file.CmsResource.CmsResourceUndoMode}
 * </ul>
 *
 * @author Michael Moossen 
 * 
 * @version $Revision: 1.1.2.1 $
 * 
 * @since 6.5.3 
 */
public abstract class A_CmsModeEnumeration implements Serializable {

    /** The internal mode descriptor. */
    private final int m_mode;

    /**
     * Default constructor.<p>
     * 
     * @param mode the internal mode descriptor
     */
    protected A_CmsModeEnumeration(int mode) {

        m_mode = mode;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {

        if (obj instanceof A_CmsModeEnumeration) {
            if (obj.getClass().equals(this.getClass())) {
                A_CmsModeEnumeration eObj = (A_CmsModeEnumeration)obj;
                return eObj.getMode() == m_mode;
            }
        }
        return false;
    }

    /**
     * Returns the mode.<p>
     *
     * @return the mode
     */
    public int getMode() {

        return m_mode;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {

        return m_mode;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {

        return String.valueOf(m_mode);
    }
}
