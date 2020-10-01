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

package org.modelio.xmi.gui.report;

import java.util.Set;
import java.util.TreeSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;

/**
 * This class represents the report model of XMI exportq
 * @author ebrosse
 */
@objid ("ad47b435-0cf4-44f1-b559-1cedf8396a26")
public class ReportModel implements IReportWriter {
    @objid ("780e078b-8f5f-4abd-b7ed-93921757400f")
    private Set<ElementMessage> errors;

    @objid ("ae3db587-d8dc-400e-a662-7b22a8df7251")
    private Set<ElementMessage> warnings;

    @objid ("299b25a4-67fd-48e0-a319-83f6f2db6a53")
    private Set<ElementMessage> infos;

    /**
     * This default constructor initializes the lists of message (errors, warning and infos)
     */
    @objid ("88cd91ed-15ab-4f3e-a447-df41307f2a0c")
    public ReportModel() {
        this.errors = new TreeSet <> ();
        this.warnings = new TreeSet <> ();
        this.infos = new TreeSet <> ();
    }

    @objid ("422c95e4-6db4-4453-8832-464ac5a50b00")
    @Override
    public void addWarning(final String initialMessage, Element element, String description) {
        String message = initialMessage;
        if (message == null) {
            message = "";
        }
        
        this.warnings.add (new ElementMessage (message, element, description));
    }

    @objid ("998e946d-037d-43d5-a605-11d8b04ff00b")
    @Override
    public void addError(final String initialMessage, Element element, String description) {
        String message = initialMessage;
        if (message == null) {
            message = "";
        }
        
        this.errors.add (new ElementMessage (message, element, description));
    }

    /**
     * This method returns the list of error message
     * 
     * @return set of error message
     */
    @objid ("44585a10-dfe9-43e4-ab22-505d5f6a5e4e")
    public Set<ElementMessage> getErrors() {
        return this.errors;
    }

    /**
     * This method returns the list of warning message
     * 
     * @return set of warning message
     */
    @objid ("faaa4657-d9f2-4d5c-9f1d-5b4584289edf")
    public Set<ElementMessage> getWarnings() {
        return this.warnings;
    }

    @objid ("196e0788-2339-4464-b7d8-555ffda0a3bc")
    @Override
    public boolean isEmpty() {
        return !(hasErrors () || hasWarnings () || hasInfos ());
    }

//    /**
//     * Adds a warning message at the existing list
//     * @param message : the text of the message
//     * @param element : the Element
//     */
//    @objid ("4634f153-19e2-4f24-9b00-b1d7d877918e")
//    public void addWarning(final String message, final Element element) {
//        this.warnings.add (new ElementMessage (message, element, ""));
//    }
//    /**
//     * Adds an error message at the existing list
//     * @param message : the text of the message
//     * @param element : the Element
//     */
//    @objid ("4ba6d701-2e9c-4bf0-93e3-df98a77c97dc")
//    public void addError(String message, Element element) {
//        this.errors.add (new ElementMessage (message, element, ""));
//    }
    @objid ("532a2cfa-5354-46ce-be80-a0ef9d51713a")
    @Override
    public void addInfo(String message, Element element, String description) {
        this.infos.add (new ElementMessage (message, element, description));
    }

//    /**
//     * Adds an info message at the existing list
//     * @param message : the text of the message
//     * @param element : the Element
//     */
//    @objid ("f51b6821-243e-412e-94e6-0e14b58f2151")
//    public void addInfo(String message, Element element) {
//        this.infos.add (new ElementMessage (message, element, ""));
//    }
    /**
     * This method returns the list of info message
     * 
     * @return set of info message
     */
    @objid ("b4d67140-a469-433f-b63b-9ba90004e017")
    public Set<ElementMessage> getInfos() {
        return this.infos;
    }

    @objid ("2f2128fa-5d92-4743-8657-dd78f96e06a6")
    @Override
    public boolean hasErrors() {
        return !this.errors.isEmpty ();
    }

    @objid ("32863972-7d48-4ab8-90c5-4408db9156c0")
    @Override
    public boolean hasInfos() {
        return !this.infos.isEmpty ();
    }

    @objid ("d6b003de-e5db-4933-bff7-da415bcb5bd2")
    @Override
    public boolean hasWarnings() {
        return !this.warnings.isEmpty ();
    }

    @objid ("d736e6b6-8036-4fbb-b341-c3bfe980587b")
    class ElementMessage implements Comparable<ElementMessage> {
        @objid ("10a70a6f-16e0-4cb1-aee2-b83eb75cb948")
        public String message;

        @objid ("3f1910d2-bda4-4c7d-abbc-2eb4f475213c")
        public String description;

        @objid ("112150ac-fb2b-40d1-be0d-99a86fdd03bf")
        public Element element;

        @objid ("93377ec9-113b-4a7d-ab8b-1888918a781a")
        ElementMessage(final String message, final Element element, final String description) {
            this.message = message;
            this.element = element;
            this.description = description;
        }

        @objid ("124ac74c-3ab1-4ad1-9409-ae4e660b6225")
        @Override
        public boolean equals(final Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ElementMessage other = (ElementMessage) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (this.description == null) {
                if (other.description != null)
                    return false;
            } else if (!this.description.equals(other.description))
                return false;
            if (this.element == null) {
                if (other.element != null)
                    return false;
            } else if (!this.element.equals(other.element))
                return false;
            if (this.message == null) {
                if (other.message != null)
                    return false;
            } else if (!this.message.equals(other.message))
                return false;
            return true;
        }

        @objid ("b538e8e0-4095-4d86-b59f-60d60de7cd5d")
        @Override
        public int compareTo(final ElementMessage anotherMessage) {
            if (this.element.equals(anotherMessage.element))
                return this.message.compareTo(anotherMessage.message) ;
            else
                return 1;
        }

        @objid ("11ea5b98-18ec-45df-8137-eade26f3799a")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
            result = prime * result + ((this.element == null) ? 0 : this.element.hashCode());
            result = prime * result + ((this.message == null) ? 0 : this.message.hashCode());
            return result;
        }

        @objid ("d8587e69-040d-45fc-8107-e95c2d44139c")
        private ReportModel getOuterType() {
            return ReportModel.this;
        }

    }

}
