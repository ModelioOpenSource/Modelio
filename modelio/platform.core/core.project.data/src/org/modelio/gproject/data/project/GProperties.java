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

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Project, fragment or module property table.
 * <p>
 * A property has a name, a string value and a definition scope.
 */
@objid ("760620c8-2ff4-11e2-8f81-001ec947ccaf")
public class GProperties implements Serializable {
    @objid ("38da71d6-0025-4962-bb68-3b69fb2a4312")
    private static final long serialVersionUID = 1L;

    @objid ("6387595e-3004-11e2-8f81-001ec947ccaf")
    private Map<String, Entry> table = new HashMap<>();

    /**
     * Initialize an empty table.
     */
    @objid ("63875966-3004-11e2-8f81-001ec947ccaf")
    public GProperties() {
        // nothing
    }

    /**
     * Copy constructor.
     * @param properties the table to copy.
     */
    @objid ("63875962-3004-11e2-8f81-001ec947ccaf")
    public GProperties(GProperties properties) {
        this.table.putAll(properties.table);
    }

    /**
     * Get a view of the table content.
     * <p>
     * 
     * The view is backed by the <tt>GProperties</tt>, so changes to the <tt>GProperties</tt> are reflected in the view, and vice-versa.
     * If the <tt>GProperties</tt> is modified while an iteration over the collection is in progress
     * (except through the iterator's own <tt>remove</tt> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the <tt>GProperties</tt>, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     * @return the table content.
     */
    @objid ("6387597a-3004-11e2-8f81-001ec947ccaf")
    public Collection<Entry> entries() {
        return this.table.values();
    }

    /**
     * Get a property.
     * @param name the property name.
     * @return the property entry or <code>null</code>.
     */
    @objid ("6387596e-3004-11e2-8f81-001ec947ccaf")
    public Entry getProperty(String name) {
        return this.table.get(name);
    }

    /**
     * Get a property value.
     * <p>
     * Return <code>null</code> if no value is defined.
     * @param name a property name.
     * @return its value or <code>null</code>
     */
    @objid ("4bd42a2a-322e-11e2-9905-001ec947ccaf")
    public String getValue(String name) {
        Entry p = this.table.get(name);
        if (p == null)
            return null;
        else
            return p.getValue();
    }

    /**
     * Get a property value.
     * <p>
     * Return the given <i>defaultValue</i> if no value is defined.
     * @param name a property name.
     * @param defaultValue the value to return if not defined
     * @return its value or the <i>defaultValue</i>
     */
    @objid ("63875969-3004-11e2-8f81-001ec947ccaf")
    public String getValue(String name, String defaultValue) {
        Entry p = this.table.get(name);
        if (p == null)
            return defaultValue;
        else
            return p.getValue();
    }

    /**
     * Merge the other properties into this one.
     * @param other the properties to merge from.
     */
    @objid ("0463309c-3019-11e2-8f81-001ec947ccaf")
    public void merge(GProperties other) {
        for (Entry e : other.entries()) {
            setProperty(e.getName(), e.getValue(), e.getScope());
        }
    }

    /**
     * Remove a property
     * @param name the property name.
     */
    @objid ("40ab4341-322d-11e2-9905-001ec947ccaf")
    public void remove(String name) {
        this.table.remove(name);
    }

    /**
     * Set a property.
     * @param name the property name.
     * @param value the property value.
     * @param scope the property scope
     */
    @objid ("63875974-3004-11e2-8f81-001ec947ccaf")
    public void setProperty(String name, String value, DefinitionScope scope) {
        Entry p = this.table.get(name);
        if (p != null) {
            p.setValue(value);
            p.setScope(scope);
        } else {
            p = new Entry(name, value, scope);
            this.table.put(name, p);
        }
    }

    @objid ("48499369-cc32-47a9-bd0b-839dc70b7068")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.table == null) ? 0 : this.table.hashCode());
        return result;
    }

    @objid ("78f963b3-655d-4528-ba69-2f33e92b1aa4")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GProperties other = (GProperties) obj;
        if (this.table == null) {
            if (other.table != null)
                return false;
        } else if (!this.table.equals(other.table))
            return false;
        return true;
    }

    @objid ("63e58144-377c-4df2-a8f9-9312291a764c")
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        
        s.append("GProperties: {");
        boolean first = true;
        for (Map.Entry<String, Entry> prop : this.table.entrySet()) {
            if (first)
                first = false;
            else
                s.append(", ");
            
            s.append(prop.getKey());
            s.append(" = '");
            Entry val = prop.getValue();
            s.append(val.getValue());
            s.append("'");
            DefinitionScope scope = val.getScope();
            if (scope != null) {
                s.append("(");
                s.append(scope.toString());
                s.append(")");
            }
        }
        s.append("}");
        return s.toString();
    }

    /**
     * Get all defined property keys in a {@link Set}.
     * @return defined property keys.
     */
    @objid ("0a5a316f-a941-40c9-b907-ca16c007247f")
    public Set<String> keys() {
        return this.table.keySet();
    }

    /**
     * A property.
     */
    @objid ("6384f70f-3004-11e2-8f81-001ec947ccaf")
    public static final class Entry {
        @objid ("061e9499-3019-11e2-8f81-001ec947ccaf")
        private DefinitionScope scope;

        @objid ("061e9491-3019-11e2-8f81-001ec947ccaf")
        private String name;

        @objid ("061e9496-3019-11e2-8f81-001ec947ccaf")
        private String value;

        /**
         * Initialize the property.
         * @param name the name
         * @param value the initial value, may be <code>null</code>.
         * @param scope the definition scope.
         */
        @objid ("6384f715-3004-11e2-8f81-001ec947ccaf")
        public Entry(String name, String value, DefinitionScope scope) {
            this.name = name;
            this.value = value;
            this.scope = scope;
        }

        /**
         * @return the property name.
         */
        @objid ("63875947-3004-11e2-8f81-001ec947ccaf")
        public String getName() {
            return this.name;
        }

        /**
         * @return the property value.
         */
        @objid ("6387594c-3004-11e2-8f81-001ec947ccaf")
        public String getValue() {
            return this.value;
        }

        /**
         * Set the value.
         * @param value the value.
         */
        @objid ("63875951-3004-11e2-8f81-001ec947ccaf")
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * @return the definition scope.
         */
        @objid ("63875955-3004-11e2-8f81-001ec947ccaf")
        public DefinitionScope getScope() {
            return this.scope;
        }

        /**
         * Set the definition scope.
         * @param scope the definition scope.
         */
        @objid ("6387595a-3004-11e2-8f81-001ec947ccaf")
        public void setScope(DefinitionScope scope) {
            this.scope = scope;
        }

        @objid ("ca985469-c003-41bd-8e07-83d2109807e3")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
            result = prime * result + ((this.scope == null) ? 0 : this.scope.hashCode());
            result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
            return result;
        }

        @objid ("f872155b-5317-4ed2-9c32-d483b9f0ce1e")
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Entry other = (Entry) obj;
            if (this.name == null) {
                if (other.name != null)
                    return false;
            } else if (!this.name.equals(other.name))
                return false;
            if (this.scope != other.scope)
                return false;
            if (this.value == null) {
                if (other.value != null)
                    return false;
            } else if (!this.value.equals(other.value))
                return false;
            return true;
        }

    }

}
