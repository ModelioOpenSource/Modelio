/* 
 * Copyright 2013-2018 Modeliosoft
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

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.AuthData;

/**
 * Unknown authentication data.
 * <p>
 * This data is never complete and serialize all its properties.
 */
@objid ("52ac863e-5976-45ce-81fe-52dff830a7d8")
public class UnknownAuthData extends AuthData {
    /**
     * The unknown scheme identifier.
     */
    @objid ("a84d7e7f-2944-4f96-b4c5-feadfae5ff82")
    private String scheme;

    /**
     * Initialize the authentication data.
     * @param scheme the unknown scheme identifier.
     */
    @objid ("b7ec47a6-54b1-4e9f-b9e5-aa7ab0df411a")
    public UnknownAuthData(String scheme) {
        this.scheme = scheme;
    }

    @objid ("1fa000ad-9446-444c-be29-4109ce558643")
    @Override
    public Map<String, String> serialize() {
        return getData();
    }

    @objid ("48035fa3-91b2-457b-b068-2d4103b60596")
    @Override
    public boolean isComplete() {
        return false;
    }

    @objid ("22d98134-965e-4f20-8511-0a9a90ae9a01")
    @Override
    public String getSchemeId() {
        return this.scheme;
    }

}
