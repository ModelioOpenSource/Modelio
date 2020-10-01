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

package org.modelio.version;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;

/**
 * Guess the Modelio version from a metamodel version.
 * @author cma
 * @since 3.6
 */
@objid ("69f6e36e-6174-41ef-a0e1-5e23ccf61d5d")
public class ModelioVersionGuesser {
    /**
     * Guess the Modelio version from the Standard metamodel version.
     * 
     * @param v The "Standard" metamodel version
     * @return the Modelio version.
     * @throws java.lang.IllegalArgumentException on invalid Modelio version
     * @throws java.lang.UnsupportedOperationException on future Modelio metamodel
     */
    @objid ("9df8fafa-86c1-41f6-a44e-294e769d2490")
    public static Version guessFromStandardMmVersion(Version v) throws IllegalArgumentException, UnsupportedOperationException {
        /*
         * Modelio releases history with metamodel versions:
         * 
         * When a metamodel version may match many Modelio version the last one should be returned.
         * 
              Modelio 3.6 - 2.0.0 : date >= 2016-10-20 
              Modelio 3.5 - 1.1.0 
              Modelio 3.4 - 9025  (date ?)
            Modelio 3.4.1.b - Build 2016-01-28-1820
            Modelio 3.4.1.a - Build 2015-12-081403
            Modelio 3.4.1 - Build 2015-11-20-1541
            Modelio 3.4.0 - Build 2015-09-16-1706
            Modelio 3.3.1 - Build 2015-02-19-1121
            Modelio 3.3.0 - Build 2015-01-19-1752
              Modelio 3.3 - 9024: 28/10/2014
              Modelio 3.3 - 9023: 23/10/2014
            Modelio 3.2.1 - Build 2014-10-31-2228
            Modelio 3.2.0 - Build 2014-10-01-1638
              Modelio 3.2 - 9022: 27/08/2014
              Modelio 3.2 - 9021: 15/01/2014
            Modelio 3.1.2 - Build 2014-05-19-1231
            Modelio 3.1.1 - Build 2014-02-21-0039
            Modelio 3.1.0 - Build 2014-01-07-1039
              Modelio 3.1 - 9020: 28/11/2013 <---- default value for ExmlFragment.getRequiredMetamodelDescriptor() if no version file
                                                   Assume Modelio 3.1 (9020) metamodel version.
              Modelio 3.1 - 9019: 08/11/2013
              Modelio 3.1 - 9018: 11/10/2013
            Modelio 3.0.1 - Build 2013-11-04-1840
            Modelio 3.0.0 - Build 2013-09-24-1706: 
              Modelio Phoenix 3.0 - 9017: 04/09/2013
              Modelio Phoenix 3.0 - 9016: 17/07/2013
              Modelio Phoenix 3.0 - 9015: 14/06/2013
              ...
              Modelio Phoenix 3.0 - 9000 :  02/2012
            Modelio 2.x :
              Modelio 2.2 - 8020 :  24/04/2012
              Modelio 2.1 - 8008 :  02/11/2011
              Modelio 2.1 - 8007 : > 25/10/2011
              Modelio 2.0 - 8006 :   29/04/2011
              Modelio 2.0 - 8005 :
              Modelio 2.0 - 8004 :
              Modelio 2.0 - 8003 :
              Modelio 2.0 - 8002 :
              Modelio 2.0 - 8001 :
            Modelio 1.x :
              Modelio 1.2 - 7008 :
              Modelio 1.1.1 & 1.2 - 7007 :
              Modelio 1.1 - 7006 :
              Modelio 1.1 - 7005 :
              Modelio 1.0 - 7004 :
         */
        if (v.compareTo(new Version(2,1,0)) >= 0) {
            // future Modelio version
            throw new UnsupportedOperationException(v.toString());
        } else if (v.compareTo(new Version(2,0,0)) >= 0) {
            return new Version(3,6,0);
        } else if (v.compareTo(new Version(1,1,0)) >= 0) {
            return new Version(3,5,0);
        } else {
            // Before Modelio 3.5 the metamodel version was a simple integer.
            int mmv = v.getBuildVersion();
            if (mmv >= 9025) {
                return new Version(3, 4, 1);
            } else if (mmv >= 9023) {
                return new Version(3, 3, 1);
            } else if (mmv >= 9021) {
                return new Version(3, 2, 1);
            } else if (mmv >= 9018) {
                return new Version(3, 1, 2);
            } else if (mmv >= 9017) {
                return new Version(3, 0, 1);
            } else if (mmv >= 9000) {
                return new Version(3, 0, 0);
            } else if (mmv >= 8020) {
                return new Version(2, 2, 0);
            } else if (mmv >= 8007) {
                return new Version(2, 1, 0);
            } else if (mmv >= 8000) {
                return new Version(2, 0, 0);
            } else if (mmv >= 7008) {
                return new Version(1, 2, 0);
            } else if (mmv >= 7005) {
                return new Version(1, 1, 0);
            } else if (mmv >= 7000) {
                return new Version(1, 0, 0);
            } else {
                // Not Modelio
                throw new IllegalArgumentException(v.toString());
            }
        }
    }

}
