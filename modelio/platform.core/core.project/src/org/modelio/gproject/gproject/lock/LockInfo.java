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

package org.modelio.gproject.gproject.lock;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.ILockInfo;
import org.modelio.vbasic.log.Log;

/**
 * Project lock information.
 * @author cmarin
 */
@objid ("1fe5f0fa-0023-479c-a9df-0c3c9bb64831")
public class LockInfo implements ILockInfo {
    @objid ("c53e11bc-c8f6-4a93-a0b4-62b4c274a113")
    private static final int VERSION = 1;

    @objid ("b182a0e7-6d90-4dd9-a914-8025f5fee54b")
    private Date date;

    @objid ("9dbd3611-f21b-451a-a8c6-7ce6f59adaa8")
    private String hostName;

    @objid ("90776ab9-c3f8-4e9e-9859-3e2eef52e712")
    private boolean isSelf;

    @objid ("c126cfd9-67ac-4faf-ab31-12aad8ce3e02")
    private String user;

    @objid ("11ec1aec-61bf-4506-84fc-319f935da931")
    private String vmIdentifier;

    /**
     * @param isSelf whether the lock is in this Java virtual machine
     * @param user the user who put the lock
     * @param hostName the machine name
     * @param jvmIdentifier a Java virtual machine identifier
     * @param date the lock date
     */
    @objid ("87703fc3-1b21-4208-acf9-3311b6731d3f")
    public LockInfo(boolean isSelf, String user, String hostName, String jvmIdentifier, Date date) {
        super();
        this.isSelf = isSelf;
        this.user = user;
        this.hostName = hostName;
        this.vmIdentifier = jvmIdentifier;
        this.date = date;
    }

    /**
     * Constructor from a {@link Properties}.
     * 
     * @param p java properties
     * @param thisVmIdentifier this Java virtual machine identifier
     */
    @objid ("9fa84816-5067-4f57-91de-19a65f3712a0")
    public LockInfo(Properties p, String thisVmIdentifier) {
        int readV = Integer.parseInt(p.getProperty("version"));
        if (readV > VERSION) {
            Log.warning(new ParseException("Future version "+readV+" of lock informations: "+p.toString(), 0));
        }
        
        this.user = p.getProperty("user");
        this.hostName = p.getProperty("hostName");
        this.vmIdentifier = p.getProperty("vmIdentifier");
        this.isSelf = thisVmIdentifier.equals(this.vmIdentifier);
        
        String sdate = p.getProperty("date");
        
        try {
            this.date = getDateFormat().parse(sdate);
        } catch (ParseException e) {
            Log.warning(e);
        }
    }

    @objid ("8807fae3-8261-4e39-89d3-9c088afc48b2")
    @Override
    public Date getDate() {
        return this.date;
    }

    @objid ("934cab15-5fef-45bb-95cb-f88f18a83c67")
    @Override
    public String getHostName() {
        return this.hostName;
    }

    @objid ("c843f387-ea6e-4378-b9fb-9b985e9d5e4c")
    @Override
    public String getOwner() {
        return this.user;
    }

    @objid ("642ac417-8b97-4c18-ba84-1a9ed335df7c")
    @Override
    public String getJvmIdentifier() {
        return this.vmIdentifier;
    }

    @objid ("c92f01df-0799-4dda-a3b1-f05844a7a645")
    @Override
    public boolean isSelf() {
        return this.isSelf;
    }

    /**
     * Serializes the lock information in a java {@link Properties}.
     * 
     * @return the saved lock infos.
     */
    @objid ("e7d17c86-ac26-406e-b1f1-ef86dd715028")
    public Properties toProperties() {
        Properties p = new Properties();
        p.setProperty("version", String.valueOf(VERSION));
        p.setProperty("vmIdentifier", this.vmIdentifier);
        p.setProperty("user",this.user);
        p.setProperty("hostName",this.hostName);
        p.setProperty("date",String.valueOf(getDateFormat().format(this.date)));
        return p;
    }

    @objid ("256b7279-edd6-49ae-bc48-ae8f25f97148")
    private static DateFormat getDateFormat() {
        return DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.US);
    }

    @objid ("e8f00d27-72d1-4cbf-afc8-1840e53e6f6b")
    @Override
    public String toString() {
        return "LockInfo [date=" + this.date + ", hostName=" + this.hostName + ", isSelf=" + this.isSelf + ", user=" + this.user + ", vmIdentifier="
                                + this.vmIdentifier + "]";
    }

    @objid ("19c395e6-169f-4b3e-acf8-bb21286c6aaa")
    @Override
    public int hashCode() {
        // isSelf is ignored
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.date == null) ? 0 : this.date.hashCode());
        result = prime * result + ((this.hostName == null) ? 0 : this.hostName.hashCode());
        result = prime * result + ((this.user == null) ? 0 : this.user.hashCode());
        result = prime * result + ((this.vmIdentifier == null) ? 0 : this.vmIdentifier.hashCode());
        return result;
    }

    @objid ("9ae65288-4c58-4157-9692-e9130ee8d929")
    @Override
    public boolean equals(Object obj) {
        // isSelf is ignored
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LockInfo other = (LockInfo) obj;
        if (this.date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!this.date.equals(other.date)) {
            return false;
        }
        if (this.hostName == null) {
            if (other.hostName != null) {
                return false;
            }
        } else if (!this.hostName.equals(other.hostName)) {
            return false;
        }
        if (this.user == null) {
            if (other.user != null) {
                return false;
            }
        } else if (!this.user.equals(other.user)) {
            return false;
        }
        if (this.vmIdentifier == null) {
            if (other.vmIdentifier != null) {
                return false;
            }
        } else if (!this.vmIdentifier.equals(other.vmIdentifier)) {
            return false;
        }
        return true;
    }

}
