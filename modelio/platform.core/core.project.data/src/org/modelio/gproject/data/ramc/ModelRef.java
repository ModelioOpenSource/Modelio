/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.gproject.data.ramc;

import java.io.Serializable;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Model object reference.
 * <p>
 * Allows to reference a model object that may or not be known.
 * The reference is made of the object metaclass name, the object
 * identifier and optionally the object name.
 */
@objid ("91f39825-295e-4182-b536-4f7f04236763")
public class ModelRef implements Serializable {
    /**
     * Metaclass name.
     */
    @objid ("103836a2-d8dc-42be-99ce-420eb81c2b0c")
    public final String mc;

    /**
     * Optional element name.
     */
    @objid ("f6109e78-0189-45b3-86aa-5d649161a808")
    public final String name;

    @objid ("0b7e278a-7fc0-4d21-8aa6-b55d8616de3f")
    private static final long serialVersionUID = 1L;

    /**
     * Element universal unique identifier.
     */
    @objid ("f7c4a386-212d-4906-bbe5-1ec72152cb51")
    public final String uuid;

    @objid ("eebfe60a-eeb5-45f1-a76d-20fdba0c327a")
    private static final Pattern pattern = Pattern.compile("'?([^']*)'?\\s?\\{(.*)\\} (.*)");

    /**
     * Creates a reference.
     * 
     * @param mc The metaclass name.
     * @param tuuid The UUID
     */
    @objid ("89557d1f-5475-4fe8-a6e0-b1eacc11fe5e")
    public ModelRef(String mc, String tuuid) {
        this.mc = mc;
        this.uuid = tuuid;
        this.name = "";
    }

    /**
     * Creates a reference.
     * 
     * @param mc The metaclass name.
     * @param tuuid The object universal identifier
     * @param name an object name, may be <code>null</code>.
     */
    @objid ("629151b6-e3b4-447b-acde-2576ca04ffdd")
    public ModelRef(String mc, String tuuid, String name) {
        this.mc = mc;
        this.uuid = tuuid;
        this.name = name;
    }

    /**
     * Construct a MRef instance from a String whose format is the MRef.toString() format so that:<br>
     * given <i>mref</i> a MRef, <code>new MRef(mref.toString()).equals(mref)</code> is guaranteed to be <code>true</code>.
     * 
     * @param s a string
     */
    @objid ("eac2fcf2-4526-4ab0-ab84-4b201533281e")
    public ModelRef(String s) {
        final Matcher m = pattern.matcher(s);
        
        if (m.matches()) {
            final MatchResult r = m.toMatchResult();
            if (r.groupCount() == 3) {
        
                this.name = (r.group(1).isEmpty() ? null : r.group(1));
                this.uuid = (r.group(2));
                this.mc = r.group(3);
            } else {
                throw new IllegalArgumentException("Invalid MRef string: " + s);
            }
        
        } else {
            throw new IllegalArgumentException("Invalid MRef string: " + s);
        }
    }

    @objid ("6659fae5-2345-45d2-9e01-edaaf4eb9d8c")
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (obj.getClass() != getClass()) {
            return false;
        }
        
        final ModelRef other = (ModelRef) obj;
        return this.mc.equals(other.mc) && this.uuid.equals(other.uuid);
    }

    @objid ("008040a3-c8a1-4e76-a8fb-11d9f84ced79")
    @Override
    public int hashCode() {
        return this.mc.hashCode() ^ this.uuid.hashCode();
    }

    /**
     * Produces a string representing this MRef object.
     * 
     * 'nnnn' {xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx} mmmmm
     * 
     * 
     * 'nnnn' = name {xxx...xx} = uuid mmmm = metaclass
     */
    @objid ("d24b0820-0a4b-4934-9602-2d49b569bb18")
    @Override
    public String toString() {
        final StringBuilder s = new StringBuilder(80);
        if (this.name != null) {
            s.append("'");
            s.append(this.name);
            s.append("' ");
        }
        s.append("{");
        s.append(this.uuid);
        s.append("} ");
        s.append(this.mc);
        return s.toString();
    }

}
