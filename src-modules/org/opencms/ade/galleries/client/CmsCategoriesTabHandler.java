/*
 * File   : $Source: /alkacon/cvs/opencms/src-modules/org/opencms/ade/galleries/client/Attic/CmsCategoriesTabHandler.java,v $
 * Date   : $Date: 2010/04/28 10:25:46 $
 * Version: $Revision: 1.1 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (C) 2002 - 2009 Alkacon Software (http://www.alkacon.com)
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

package org.opencms.ade.galleries.client;

/**
 * The categories tab handler.<p>
 * 
 * This class receives events information from the categories tab and 
 * delegates them to the gallery controller.
 * 
 * @author Polina Smagina
 * 
 * @version $Revision: 1.1 $ 
 * 
 * @since 8.0.0
 */
public class CmsCategoriesTabHandler extends A_CmsTabHandler {

    /**
     * Constructor.<p>
     * 
     * @param controller the gallery controller
     */
    public CmsCategoriesTabHandler(CmsGalleryController controller) {

        super(controller);
    }

    /**
     * Will be triggered when the user unchecks the checkbox to deselect a category.<p>
     * 
     * @param categoryPath the category path as id
     */
    public void onDeselectCategory(String categoryPath) {

        m_controller.removeCategory(categoryPath);
    }

    /**
     * Will be triggered when the user checks the checkbox to select a category.<p>
     * 
     * @param categoryPath the category path as id
     */
    public void onSelectCategory(String categoryPath) {

        m_controller.addCategory(categoryPath);
    }

    /**
     * Will be triggered when the user selects the categories tab.<p>
     */
    public void onSelection() {

        m_controller.updateCategoriesTab();
    }
}