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
package org.modelio.platform.search.engine;

import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("17891a42-162e-4650-8489-4cedc20fe162")
public class DefaultSearchCriteria implements ISearchCriteria {
    @objid ("83c85443-7837-4665-8fec-be3f21284370")
    Properties options = new Properties();

    @objid ("d757beaf-2cc5-4c0c-8e65-50d60e219cff")
    @Override
    public Object getOption(String key) {
        return this.options.get(key);
    }

    @objid ("6f630096-0712-49e3-936a-4c28cdf43226")
    @Override
    public void setOption(String key, Object value) {
        this.options.put(key, value);
    }

}
