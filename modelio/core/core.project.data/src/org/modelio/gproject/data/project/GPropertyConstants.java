/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.gproject.data.project;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Well known project/part property constants
 * 
 * @author cmarin
 * @since 5.5 - 29/08/2023
 */
@objid ("45627272-e33a-436b-8370-1fa93e970771")
public final class GPropertyConstants {
    /**
     * Property that tells the fragment is read only.
     * <p>
     * This property must be supported by all fragment implementations.
     * <p>
     * @deprecated Replaced in Modelio 5.5 by {@link Access#WRITE}.
     */
    @objid ("b9b04627-3f94-483c-878f-a8fc496065a3")
    @Deprecated
    public static final String PROP_READ_ONLY = "readonly";

    /**
     * Access rights constants
     */
    @objid ("2e69dd18-d700-4f8a-a986-c3fba432882c")
    public static final class Access {
        /**
         * If false, the part is not visible
         * 
         * @since Server 4.3 + Modelio 5.5 - 29/08/2023
         * 
         */
        @objid ("3e14fc95-5e91-4119-ad66-a30509a6d694")
        public static final String VISIBLE = "access.visible";

        /**
         * Equals {@link #PROP_READ_ONLY}
         * 
         * @since Server 4.3 + Modelio 5.5 - 29/08/2023
         */
        @objid ("7d68b6da-b89d-4b88-8435-c5023b2f8a78")
        public static final String WRITE = "access.write";

    }

}
