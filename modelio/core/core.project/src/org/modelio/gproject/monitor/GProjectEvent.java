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
package org.modelio.gproject.monitor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Event in the GProject life cycle.
 */
@objid ("599eea51-d1c8-47d3-9fa3-320252976067")
public class GProjectEvent {
    /**
     * The event type.
     */
    @objid ("7bd2f91e-5b36-40fe-ac2e-2d2e4a49af77")
    public final GProjectEventType type;

    /**
     * An event message.
     */
    @objid ("7a429f33-c99e-45da-8e1e-87ac43907699")
    public final String message;

    /**
     * The subject of the event. May be <code>null</code>.
     */
    @objid ("0f1029cb-8b55-40ae-aae5-7ddba5541f94")
    public final Object subject;

    /**
     * An optional exception.
     */
    @objid ("abf49d48-10f5-4e97-b569-0d1af597f91f")
    public final Throwable throwable;

    /**
     * Initialize an event.
     * @param type the event type
     * @param message the event message. May be <code>null</code>.
     * @param subject the related object, may be <code>null</code>.
     * @param throwable the exception that caused the event, may be <code>null</code>.
     */
    @objid ("fab5ca70-bb50-4855-9115-6cc6eed3d407")
    public  GProjectEvent(GProjectEventType type, String message, Object subject, Throwable throwable) {
        this.type = type;
        this.subject = subject;
        this.message = message;
        this.throwable = throwable;
        
    }

    @objid ("a1f87b5b-2058-478f-a259-e2e421291648")
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.type);
        if (this.message != null) {
            s.append(": ");
            s.append(this.message);
        }
        
        if (this.subject != null) {
            s.append(" on '");
            s.append(this.subject.toString());
            s.append("'");
        }
        
        if (this.throwable != null) {
            s.append(", error =");
            s.append(this.throwable.toString());
        }
        return s.toString();
    }

    /**
     * Instantiate a warning event for a subject.
     * @param subject the involved object
     * @param e an exception
     * @return the built warning
     */
    @objid ("18882c59-0977-4b53-b0e0-d7b6c9b686df")
    public static GProjectEvent buildWarning(Object subject, Throwable e) {
        GProjectEvent ev = new GProjectEvent(GProjectEventType.WARNING,
                e.getLocalizedMessage(),
                subject,
                e.getCause());
        return ev;
    }

    /**
     * Instantiate a warning event for a subject.
     * @param subject the involved object
     * @param message the warning message
     * @return the built warning
     */
    @objid ("28b7b9a1-7826-4c90-b2b7-5c3dce9bcf9b")
    public static GProjectEvent buildWarning(Object subject, String message) {
        GProjectEvent ev = new GProjectEvent(GProjectEventType.WARNING,
                message,
                subject,
                null);
        return ev;
    }

}
