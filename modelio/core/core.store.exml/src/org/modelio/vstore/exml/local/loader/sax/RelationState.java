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

package org.modelio.vstore.exml.local.loader.sax;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.DuplicateObjectException;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vstore.exml.common.index.IndexException;
import org.modelio.vstore.exml.common.model.IllegalReferenceException;
import org.modelio.vstore.exml.common.model.ObjIdName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Read 'COMP' and 'LINK' tags.
 */
@objid ("2afeb991-3faf-11e2-87cb-001ec947ccaf")
class RelationState extends AbstractState {
    @objid ("2b011b93-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (localName) {
        case TAG_COMP:
        case TAG_LINK:
        case TAG_EXTDEP:
            getDataModel().getCurrent().updateCurrentDependency();
            this.stateHandler.enterOBJECTState();
            break;
        default:
            break;
        }
    }

    @objid ("2afeb993-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        try {
            ObjIdName objid;
            SmObjectImpl obj;
            switch (localName) {
            case TAG_COMPID:
                objid = readID(atts);
                if (objid != null) {
                    obj = getObjRef(objid);
        
                    if (obj != null) {
                        getDataModel().getCurrent().addToDep(obj);
                    }
                }
        
                break;
            case TAG_REFOBJ:
                this.stateHandler.enterREFOBJState();
                break;
            case TAG_ID:
            case TAG_FOREIGNID:
                objid = readID(atts);
                if (objid != null) {
                    getDataModel().addDepRef(objid);
                }
                break;
            case TAG_OBJECT:
                getDataModel().getCurrent().setCurrentState(this);
                this.stateHandler.enterOBJECTState();
                break;
            default:
                throwInvalidTag(localName);
                break;
        
            }
        } catch (DuplicateObjectException e) {
            rethrowException(e);
        } catch (RuntimeException e) {
            rethrowException(e);
        } catch (IndexException e) {
            rethrowException(e);
        } catch (IllegalReferenceException e) {
            rethrowException(e);
        }
    }

    @objid ("6050a96f-dab6-4d6a-8eb8-9ac2ff410284")
    private SmObjectImpl getObjRef(ObjIdName objid) throws DuplicateObjectException, IllegalReferenceException, IndexException {
        SmObjectImpl obj;
        obj = getDataModel().loadHelper.getLoadedObject(objid.toObjId());
        if (obj == null) {
            obj = getDataModel().loadHelper.getRefObject(getDataModel().modelLoader, objid);
        }
        return obj;
    }

}
