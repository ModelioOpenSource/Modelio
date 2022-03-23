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
package org.modelio.vbasic.version;

import java.io.Serializable;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Version identifier for Modelio and modules.
 * <p>
 * Version identifiers have three components:
 * <ul>
 * <li>Major version
 * <li>Minor version
 * <li>Build version
 * </ul>
 * <p>
 * Two Versions are considered equal if all components are equal.
 */
@objid ("063d7a23-c9cc-11e1-8052-001ec947ccaf")
public class Version implements Comparable<Version>, Serializable {
    @objid ("2bc70e01-4e43-40e2-b212-b29f1da9f6d5")
    private static final long serialVersionUID = 1L;

    @objid ("063d79e6-c9cc-11e1-8052-001ec947ccaf")
    private final int buildVersion;

    @objid ("063d7a2d-c9cc-11e1-8052-001ec947ccaf")
    private final int majorVersion;

    @objid ("063d7a2f-c9cc-11e1-8052-001ec947ccaf")
    private final int minorVersion;

    /**
     * Instantiate a new Version from its component.<br>
     * The standard format is <b>Major.Minor.Build.Metamodel</b>.
     * @param major the first component of the version.
     * @param minor the second component of the version.
     * @param build the third component of the version.
     */
    @objid ("063d7a20-c9cc-11e1-8052-001ec947ccaf")
    public  Version(int major, int minor, int build) {
        this.majorVersion = major;
        this.minorVersion = minor;
        this.buildVersion = build;
        
    }

    /**
     * Instantiate a new Version from a String. The standard format is
     * <b>Major.Minor.Build</b>.
     * @param versionString The String to parse to create the version.
     * @throws NumberFormatException thrown when the parameter doesn't have a valid format.
     */
    @objid ("063d7a24-c9cc-11e1-8052-001ec947ccaf")
    public  Version(String versionString) throws NumberFormatException {
        String[] versionNumbers = versionString.trim().split("\\.", 4);
        
        if ((versionNumbers.length > 0) && (versionNumbers[0].length() != 0)) {
            this.majorVersion = Integer.parseInt(versionNumbers[0]);
        } else {
            this.majorVersion = 0;
        }
        
        if ((versionNumbers.length > 1) && (versionNumbers[1].length() != 0)) {
            this.minorVersion = Integer.parseInt(versionNumbers[1]);
        } else {
            this.minorVersion = 0;
        }
        
        if ((versionNumbers.length > 2) && (versionNumbers[2].length() != 0)) {
            this.buildVersion = parseRevision(versionNumbers[2]);
        } else {
            this.buildVersion = 0;
        }
        
    }

    @objid ("d17c35e0-e40b-485d-bee0-f543d66f872a")
    @Override
    public int compareTo(Version other) {
        if (this.majorVersion > other.majorVersion) {
            return 100;
        }
        if (this.majorVersion < other.majorVersion) {
            return -100;
        }
        
        // Major are the same, test minor number
        if (this.minorVersion > other.minorVersion) {
            return 10;
        }
        if (this.minorVersion < other.minorVersion) {
            return -10;
        }
        
        // Major and minor are the same, test build number
        if (this.buildVersion > other.buildVersion) {
            return 1;
        }
        if (this.buildVersion < other.buildVersion) {
            return -1;
        }
        return 0;
    }

    /**
     * Compares this Version to the specified object.
     * <p>
     * The result is true if and only if the argument is not null and is a
     * Version object that represents the same version object as this object.
     * <p>
     * Two Versions are considered equal if the only difference is one of their
     * metamodel version being zero.
     * @param anObject The object to compare this Version against.
     * @return true if the given object represents a Version equivalent to this
     * Version, false otherwise.
     */
    @objid ("063d7a25-c9cc-11e1-8052-001ec947ccaf")
    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Version) {
            Version other = (Version) anObject;
            return ((this.majorVersion == other.majorVersion)
                    && (this.minorVersion == other.minorVersion)
                    && (this.buildVersion == other.buildVersion));
        }
        return false;
    }

    /**
     * Get the build number of this version
     * @return the build number of this version.
     */
    @objid ("063d7a26-c9cc-11e1-8052-001ec947ccaf")
    public int getBuildVersion() {
        return this.buildVersion;
    }

    /**
     * Get the major number of this version.
     * @return the major number of this version.
     */
    @objid ("063d7a27-c9cc-11e1-8052-001ec947ccaf")
    public int getMajorVersion() {
        return this.majorVersion;
    }

    /**
     * Get the minor number of this version.
     * @return the minor number of this version.
     */
    @objid ("063d7a29-c9cc-11e1-8052-001ec947ccaf")
    public int getMinorVersion() {
        return this.minorVersion;
    }

    @objid ("063d7a31-c9cc-11e1-8052-001ec947ccaf")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + this.buildVersion;
        result = (prime * result) + this.majorVersion;
        result = (prime * result) + this.minorVersion;
        return result;
    }

    /**
     * Tells whether the only difference between this version and the other
     * is that this version build is newer than the other.
     * @param other another version to compare to.
     * @return true only if this version has just a newer build.
     */
    @objid ("5fd47d20-e3a8-480d-8411-78cb3046bf50")
    public boolean isNewerBuildOf(Version other) {
        return (other != null
                && this.majorVersion == other.majorVersion
                && this.minorVersion == other.minorVersion
                && this.buildVersion > other.buildVersion);
        
    }

    /**
     * Check if this Version is same or newer than the given Version.
     * @param other the Version object that must be compared to this Version.
     * @return <i>true</i> if this is same or newer than the given version.
     */
    @objid ("021012be-0e6c-4d08-aea3-0cf172ff62dd")
    public boolean isNewerOrSameThan(Version other) {
        return compareTo(other) >= 0;
    }

    /**
     * Check if this Version is strictly newer than the given Version.
     * @param other the Version object that must be compared to this Version.
     * @return <i>true</i> if this is newer than the given version.
     */
    @objid ("063d7a2a-c9cc-11e1-8052-001ec947ccaf")
    public boolean isNewerThan(Version other) {
        return compareTo(other) > 0;
    }

    /**
     * Check if this Version is same or older than the given Version.
     * <p>
     * @param other the Version object that must be compared to this Version.
     * @return <i>true</i> if this is older or same as the given version.
     */
    @objid ("11245877-935d-44f3-a5ab-748feb83a297")
    public boolean isOlderOrSameThan(Version other) {
        return compareTo(other) <= 0;
    }

    /**
     * Check if this Version is strictly older than the given Version.
     * @param other the Version object that must be compared to this Version.
     * @return <i>true</i> if this is older than the given version.
     */
    @objid ("063d7a2b-c9cc-11e1-8052-001ec947ccaf")
    public boolean isOlderThan(Version other) {
        return compareTo(other) < 0;
    }

    /**
     * Provides a formatted string representation of the version.
     * <P>
     * The format is: V.R.CC where:
     * </P>
     * <UL>
     * <LI>V is the major version number (one or more digits)</LI>
     * <LI>R is the minor version number (one or more digits)</LI>
     * <LI>CC is the correction level (0-padded to 2 digits)</LI>
     * </UL>
     * @return the formatted string representation of the version.
     */
    @objid ("063d7a2c-c9cc-11e1-8052-001ec947ccaf")
    @Override
    public String toString() {
        StringBuilder stringBuf = new StringBuilder();
        stringBuf.append(this.majorVersion);
        stringBuf.append('.');
        stringBuf.append(this.minorVersion);
        stringBuf.append('.');
        stringBuf.append(String.format("%02d", this.buildVersion));
        return stringBuf.toString();
    }

    /**
     * Provides a formatted string representation of the version.
     * @param format <P>
     * The format is: VRC where:
     * <UL>
     * <LI>V means 'prints the major version number' (one or more digits)</LI>
     * <LI>R means 'prints the minor version number (one or more digits)</LI>
     * <LI>C means 'print the correction level (0-padded to 2 digits) </LI>
     * <LI>other chars are just inserted in the resulting string.
     * There is no way of escaping characters VRC
     * </UL>
     * </P>
     * 
     * example: if version is 3.0.01
     * 
     * version.toString("V.R") will produce 3.0
     * version.toString("V.R-C") will produce 3.0-01
     * @return the formatted string representation of the version.
     */
    @objid ("5de000a8-66df-4b8a-9095-015af24b6505")
    public String toString(String format) {
        StringBuilder buf = new StringBuilder();
        
        for (char c : format.toUpperCase().toCharArray()) {
            switch (c) {
            case 'V':
                buf.append(this.majorVersion);
                break;
            case 'R':
                buf.append(this.minorVersion);
                break;
            case 'C':
                buf.append(String.format("%02d", this.buildVersion));
                break;
            default:
                buf.append(c);
                break;
            }
        }
        return buf.toString();
    }

    /**
     * Parse the revision number. It may be a decimal number or for ascendant
     * compatibility a letter. In the second case it is parsed as a base 36
     * number.
     * @param revision revision number
     * @return the parsed revision number
     * @throws NumberFormatException if the revision cannot be parsed.
     */
    @objid ("063d7a30-c9cc-11e1-8052-001ec947ccaf")
    private static int parseRevision(String revision) throws NumberFormatException {
        try {
            return Integer.parseInt(revision);
        } catch (NumberFormatException e) {
            // May be old revision number with digits
            return Integer.parseInt(revision, 36);
        }
        
    }

    /**
     * Return a copy of this version with the build number modified.
     * @param newBuild the new build version.
     * @return a modified Version.
     */
    @objid ("b23cec74-1bdc-4ba6-ba14-337da0d244bb")
    public Version withBuild(int newBuild) {
        return new Version(this.majorVersion, this.minorVersion, newBuild);
    }

    /**
     * Return a copy of this version with the build number zeroed .
     * @return a modified Version.
     */
    @objid ("68b59b12-c766-44ca-9942-8bed5110418f")
    public Version withoutBuild() {
        return withBuild(0);
    }

}
