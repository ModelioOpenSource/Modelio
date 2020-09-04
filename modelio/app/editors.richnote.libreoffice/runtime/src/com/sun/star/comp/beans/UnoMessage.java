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

package com.sun.star.comp.beans;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.beans.PropertyValue;
import com.sun.star.container.XNameAccess;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.uno.AnyConverter;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

/**
 * Tries to access localized OOO error messages.
 * @Deprecated not implemented, may not even work
 */
@objid ("cc37de9e-c069-472b-82db-d81428e2a181")
@Deprecated
class UnoMessage {
    @objid ("d9e015a9-b178-4f7a-9aac-77f81d834b9a")
    public static final int STRINGS_NUM = 33;

    @objid ("8f394968-bdbd-42b9-b2eb-81eb83a05fd1")
    private static String[] m_pEntryNames;

    @objid ("866bc866-9956-477b-9c0c-605778019f2e")
    private static String[] m_pConfigStrings;

    @objid ("e18eabf7-2257-4014-aa30-deaac2b22b26")
    public static synchronized String GetLocalizedString(XComponentContext xContext, int nID) throws com.sun.star.uno.Exception {
        if (nID >= STRINGS_NUM)
            throw new com.sun.star.uno.RuntimeException();
        
        if (m_pConfigStrings == null) {
            XNameAccess xNameAccess = GetConfigNameAccess(xContext,
                                                          "org.openoffice.Office.Custom.WikiExtension/Strings");
        
            String[] pStrings = new String[STRINGS_NUM];
            for (int nInd = 0; nInd < STRINGS_NUM; nInd++)
                if (m_pEntryNames[nInd] != null)
                    pStrings[nInd] = AnyConverter.toString(xNameAccess.getByName(m_pEntryNames[nInd]));
                else
                    pStrings[nInd] = "";
        
            m_pConfigStrings = pStrings;
        }
        return m_pConfigStrings[nID];
    }

    @objid ("cfec0a25-261b-4d7f-99d3-366f497143d3")
    protected static XNameAccess GetConfigNameAccess(XComponentContext xContext, String sNodepath) throws com.sun.star.uno.Exception {
        XNameAccess xNameAccess = UnoRuntime.queryInterface(XNameAccess.class,
                                                            GetConfig(xContext, sNodepath, false));
        if (xNameAccess == null)
            throw new com.sun.star.uno.RuntimeException();
        return xNameAccess;
    }

    @objid ("4c9e04d4-20f9-4471-b959-7c874513097e")
    protected static Object GetConfig(XComponentContext xContext, String sNodepath, boolean bWriteAccess) throws com.sun.star.uno.Exception {
        if (xContext == null || sNodepath == null)
            throw new com.sun.star.uno.RuntimeException();
        
        PropertyValue aVal = new PropertyValue();
        aVal.Name = "nodepath";
        aVal.Value = sNodepath;
        Object[] aArgs = new Object[1];
        aArgs[0] = aVal;
        return GetConfigurationProvider(xContext).createInstanceWithArguments((bWriteAccess
                                                                                              ? "com.sun.star.configuration.ConfigurationUpdateAccess"
                                                                                              : "com.sun.star.configuration.ConfigurationAccess"),
                                                                                      aArgs);
    }

    @objid ("ca259235-1ad5-4846-bdca-0d8e722ce7bc")
    protected static XMultiServiceFactory GetConfigurationProvider(XComponentContext xContext) throws com.sun.star.uno.Exception {
        XMultiServiceFactory xConfigurationProvider = null;
        if (xContext != null) {
            XMultiComponentFactory xFactory = xContext.getServiceManager();
            Object oConfigProvider = xFactory.createInstanceWithContext("com.sun.star.configuration.ConfigurationProvider",
                                                                        xContext);
            xConfigurationProvider = UnoRuntime.queryInterface(XMultiServiceFactory.class, oConfigProvider);
        }
        
        if (xConfigurationProvider == null)
            throw new com.sun.star.uno.RuntimeException();
        return xConfigurationProvider;
    }

}
