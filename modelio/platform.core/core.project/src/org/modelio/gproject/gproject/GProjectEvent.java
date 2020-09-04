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

package org.modelio.gproject.gproject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.fragment.FragmentState;
import org.modelio.gproject.fragment.IProjectFragment;

/**
 * Event in the GProject life cycle.
 */
@objid ("8e7a0917-08b2-11e2-b193-001ec947ccaf")
public class GProjectEvent {
    /**
     * The event type.
     */
    @objid ("618b7790-08b6-11e2-b193-001ec947ccaf")
    public final GProjectEventType type;

    /**
     * An event message.
     */
    @objid ("618dd9df-08b6-11e2-b193-001ec947ccaf")
    public final String message;

    /**
     * The fragment concerned by the event. May be <code>null</code>.
     */
    @objid ("6181ee38-08b6-11e2-b193-001ec947ccaf")
    public final IProjectFragment fragment;

    /**
     * An optional exception.
     */
    @objid ("6181ee3a-08b6-11e2-b193-001ec947ccaf")
    public final Throwable throwable;

    /**
     * Initialize an event.
     * 
     * @param type the event type
     * @param message the event message. May be <code>null</code>.
     * @param fragment the related fragment, may be <code>null</code>.
     * @param throwable the exception that caused the event, may be <code>null</code>.
     */
    @objid ("0ceb3d8d-6f93-406b-980f-6a1eee957317")
    public GProjectEvent(GProjectEventType type, String message, IProjectFragment fragment, Throwable throwable) {
        this.type = type;
        this.fragment=fragment;
        this.message = message;
        this.throwable = throwable;
    }

    @objid ("6fc1698e-6fb6-45c7-911b-11574fbacb7a")
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.type);
        if (this.message != null) {
            s.append(": ");
            s.append(this.message);
        }
        
        if (this.fragment != null) {
            s.append(" on '");
            s.append(this.fragment.getId());
            s.append("' fragment");
        }
        
        if (this.throwable != null) {
            s.append(", error =");
            s.append(this.throwable.toString());
        }
        return s.toString();
    }

    /**
     * Instantiate a warning event for a fragment.
     * 
     * @param f the fragment
     * @param e an exception
     * @return the built warning
     */
    @objid ("6181ee44-08b6-11e2-b193-001ec947ccaf")
    public static GProjectEvent buildWarning(IProjectFragment f, Throwable e) {
        GProjectEvent ev = new GProjectEvent(GProjectEventType.WARNING,
                e.getLocalizedMessage(),
                f,
                e);
        return ev;
    }

    /**
     * Instantiate a warning event for a fragment.
     * 
     * @param f the involved fragment
     * @param e an exception
     * @return the built warning
     */
    @objid ("6181ee4a-08b6-11e2-b193-001ec947ccaf")
    public static GProjectEvent buildWarning(IProjectFragment f, Exception e) {
        GProjectEvent ev = new GProjectEvent(GProjectEventType.WARNING,
                e.getLocalizedMessage(),
                f,
                e);
        return ev;
    }

    /**
     * Instantiate an event for a fragment down event.
     * 
     * @param abstractFragment the fragment that went down.
     * @return the event.
     */
    @objid ("6181ee3e-08b6-11e2-b193-001ec947ccaf")
    public static GProjectEvent FragmentDownEvent(IProjectFragment abstractFragment) {
        GProjectEvent ev = new GProjectEvent(GProjectEventType.FRAGMENT_DOWN, 
                abstractFragment.getDownError().getLocalizedMessage(),
                abstractFragment,
                abstractFragment.getDownError());
        return ev;
    }

    /**
     * Instantiate a fragment added event.
     * 
     * @param afragment the fragment
     * @return the built event
     */
    @objid ("4a68ff47-5014-4642-8add-fd28ed1c9aa5")
    public static GProjectEvent fragmentAdded(IProjectFragment afragment) {
        GProjectEvent ev = new GProjectEvent(GProjectEventType.FRAGMENT_ADDED,
                null,
                afragment,
                null);
        return ev;
    }

    /**
     * Instantiate a fragment added event.
     * 
     * @param afragment the fragment
     * @return the built event
     */
    @objid ("3ef4d2fb-54f9-4da8-bf42-72ceab8a4fe8")
    public static GProjectEvent fragmentRemoved(IProjectFragment afragment) {
        GProjectEvent ev = new GProjectEvent(GProjectEventType.FRAGMENT_REMOVED,
                null,
                afragment,
                null);
        return ev;
    }

    /**
     * Instantiate a fragment state changed event.
     * 
     * @param afragment the changed fragment
     * @return the built event.
     */
    @objid ("d71bac30-d39c-4cd6-8e66-0b54dad174e9")
    public static GProjectEvent fragmentStateChanged(IProjectFragment afragment) {
        if (afragment.getState() == FragmentState.DOWN) {
            return FragmentDownEvent(afragment);
        } else {
            GProjectEvent ev = new GProjectEvent(GProjectEventType.FRAGMENT_STATE_CHANGED,
                    null,
                    afragment,
                    null);
        
            return ev;
        }
    }

    /**
     * Instantiate a warning event for the project.
     * 
     * @param e an exception
     * @return the built warning
     */
    @objid ("6ce80c0e-7189-481e-aee3-e37b74fad9e9")
    public static GProjectEvent buildWarning(Exception e) {
        GProjectEvent ev = new GProjectEvent(GProjectEventType.WARNING,
                e.getLocalizedMessage(),
                null,
                e);
        return ev;
    }

    /**
     * Instantiate a warning event for a fragment.
     * 
     * @param f the involved fragment
     * @param message the warning message
     * @return the built warning
     */
    @objid ("f997d13b-c8c9-4bf0-b013-9d4f7f2ebaf6")
    public static GProjectEvent buildWarning(IProjectFragment f, String message) {
        GProjectEvent ev = new GProjectEvent(GProjectEventType.WARNING,
                message,
                f,
                null);
        return ev;
    }

}
