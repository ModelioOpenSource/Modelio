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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.impl.uml.infrastructure.properties;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.ElementImpl;
import org.modelio.metamodel.impl.uml.infrastructure.properties.PropertyTableData;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixValueDefinition;
import org.modelio.metamodel.uml.infrastructure.matrix.QueryDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00691798-ec87-1098-b22e-001ec947cd2a")
public class PropertyTableImpl extends ElementImpl implements PropertyTable {
    @objid ("45603d41-d582-4fc0-83ff-a25543c4c6f2")
    private int tableHash;

    @objid ("c8640425-848f-4d88-a567-c5c74b734412")
    private SoftReference<Properties> table;

    @objid ("71fabe31-282d-11e2-bf07-001ec947ccaf")
    @Override
    public String getProperty(String key) {
        Properties props = getSyncProperties();
        return props.getProperty(key);
    }

    @objid ("71fabe37-282d-11e2-bf07-001ec947ccaf")
    @Override
    public final Properties toProperties() throws IllegalStateException {
        Properties props = new Properties();
        props.putAll(getSyncProperties());
        return props;
    }

    @objid ("71fabe3c-282d-11e2-bf07-001ec947ccaf")
    @Override
    public final void setContent(Properties newContent) {
        StringWriter sw = new StringWriter();
        try {
            newContent.store(sw, null);
            final String s = sw.toString();
            setContent(s);
        
            // Update the cache
            this.table = new SoftReference<>(newContent);
            this.tableHash = s.hashCode();
        } catch (IOException e) {
            throw new Error(e); // cannot happen
        }
    }

    @objid ("71fabe40-282d-11e2-bf07-001ec947ccaf")
    @Override
    public void setProperty(String key, String value) {
        Properties props = getSyncProperties();
        
        props.setProperty(key, value);
        
        setContent(props);
    }

    /**
     * Update the cached Properties from the string content.
     * @return the new cached Properties.
     */
    @objid ("b5b0b9f3-8d2c-44a1-8173-3c97f0a44fe1")
    private Properties computeTable() {
        Properties props = new Properties();
        try {
            final String strContent = getContent();
            props.load(new StringReader(strContent));
            this.table = new SoftReference<>(props);
            this.tableHash = strContent.hashCode();
        } catch (IOException e) {
            throw new IllegalStateException("The 'Content' attribute has not the java.util.Properties format:"
                    + e.getLocalizedMessage(), e);
        }
        return props;
    }

    /**
     * Get the internal property table.
     * <p>
     * The internal property table reflects the string content at the moment it is computed.
     * @return the internal property table.
     */
    @objid ("0ab19bd3-e3e4-41f5-be52-5cee16a1623e")
    private Properties getSyncProperties() {
        final Properties syncTable = isTableSync();
        
        if (syncTable == null)
            return computeTable();
        else
            return syncTable;
    }

    /**
     * Return the cached properties if the Properties is up to date with the string content.
     * @return the cached property table or <code>null</code>.
     */
    @objid ("07ffcaec-5a48-4ecb-ba9d-767191ca6ac7")
    private Properties isTableSync() {
        if (this.table == null)
            return null;
        
        Properties props = this.table.get();
        if (props == null)
            return null;
        
        if (this.tableHash != getContent().hashCode())
            return null;
        return props;
    }

    @objid ("e8cfe84a-b9e4-47cd-a967-f7b359006dde")
    @Override
    public String getName() {
        return (String) getAttVal(((PropertyTableSmClass)getClassOf()).getNameAtt());
    }

    @objid ("03d0f48b-fab0-422c-b3ad-0b66491eb10e")
    @Override
    public void setName(String value) {
        setAttVal(((PropertyTableSmClass)getClassOf()).getNameAtt(), value);
    }

    @objid ("da032541-6444-4691-b945-fb21a7700a0e")
    @Override
    public String getContent() {
        return (String) getAttVal(((PropertyTableSmClass)getClassOf()).getContentAtt());
    }

    @objid ("4b03d2da-ada6-4fad-b516-2c580993472b")
    @Override
    public void setContent(String value) {
        setAttVal(((PropertyTableSmClass)getClassOf()).getContentAtt(), value);
    }

    @objid ("0fe60a15-4969-4b5b-b23c-120b5ae873bf")
    @Override
    public MatrixValueDefinition getOwnerValDef() {
        Object obj = getDepVal(((PropertyTableSmClass)getClassOf()).getOwnerValDefDep());
        return (obj instanceof MatrixValueDefinition)? (MatrixValueDefinition)obj : null;
    }

    @objid ("a0b0ea52-bef4-4823-9927-3bbb024cd7cc")
    @Override
    public void setOwnerValDef(MatrixValueDefinition value) {
        appendDepVal(((PropertyTableSmClass)getClassOf()).getOwnerValDefDep(), (SmObjectImpl)value);
    }

    @objid ("b3a67799-45c5-4d06-92b3-c57b96ad5b13")
    @Override
    public QueryDefinition getOwnerQuery() {
        Object obj = getDepVal(((PropertyTableSmClass)getClassOf()).getOwnerQueryDep());
        return (obj instanceof QueryDefinition)? (QueryDefinition)obj : null;
    }

    @objid ("90605d31-5fe6-4c8e-90b2-c73ae2e9d7a2")
    @Override
    public void setOwnerQuery(QueryDefinition value) {
        appendDepVal(((PropertyTableSmClass)getClassOf()).getOwnerQueryDep(), (SmObjectImpl)value);
    }

    @objid ("5bc4aaa5-6d15-45f8-9525-4e830b702ada")
    @Override
    public ModelElement getOwner() {
        Object obj = getDepVal(((PropertyTableSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof ModelElement)? (ModelElement)obj : null;
    }

    @objid ("3e26a9c3-01be-4397-95b1-4feae485c71a")
    @Override
    public void setOwner(ModelElement value) {
        appendDepVal(((PropertyTableSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("c0c8e2ea-d2cd-4152-8bad-2e50f09562c9")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // OwnerValDef
        obj = (SmObjectImpl)this.getDepVal(((PropertyTableSmClass)getClassOf()).getOwnerValDefDep());
        if (obj != null)
          return obj;
        // OwnerQuery
        obj = (SmObjectImpl)this.getDepVal(((PropertyTableSmClass)getClassOf()).getOwnerQueryDep());
        if (obj != null)
          return obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((PropertyTableSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("a979f257-256d-4f2f-aa6b-7be54a576985")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // OwnerValDef
        dep = ((PropertyTableSmClass)getClassOf()).getOwnerValDefDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // OwnerQuery
        dep = ((PropertyTableSmClass)getClassOf()).getOwnerQueryDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        // Owner
        dep = ((PropertyTableSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("517fbd53-6f47-490d-a842-bf255bcbf6f4")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitPropertyTable(this);
    }

}
