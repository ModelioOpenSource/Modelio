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
package org.modelio.diagram.elements.core.link.anchors;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;

/**
 * Helper class to choose a good anchor reference point one or more set of points.
 * <p>
 * A good anchor reference point is {@link Point} that is:<ul>
 * <li> non null
 * <li> non zero (0, 0)
 * <li> not a forbidden point, usually the center of a figure.
 * </ul>
 * 
 * @author cma
 * @since 5.1
 */
@objid ("01aa1795-9b92-4610-b7d0-b76b7e6a5af7")
public class AnchorRefHelper {
    @objid ("05d77bb4-af42-46fc-aef6-8eab6555af9f")
    private  AnchorRefHelper() {
        // no instance
    }

    /**
     * Choose the first good point in the given list
     * @param candidates the point candidates
     * @param first it true begin at the list start. If false process from the end in the reverse order.
     * @param len the number of points to test in the candidate list.
     * @param forbidden a bad reference point to avoid
     * @return the found good reference point, null if none .
     */
    @objid ("3832521f-9411-487e-b440-d5f8f4055303")
    public static Point findGoodAnchorRef(List<? extends Point> candidates, boolean first, int len, Point forbidden) {
        if (candidates==null)
            return null;
        if (candidates.isEmpty())
            return null;
        
        if (first) {
            int stop = Math.min(len, candidates.size());
            for (int i = 0; i < stop; i++) {
                Point candidate = candidates.get(i);
                if (isGoodAnchorRef(candidate, forbidden))
                    return candidate;
            }
        } else {
            int i = candidates.size() - 1;
            int stop = i - len;
            for (; i > stop && i >= 0; i--) {
                Point candidate = candidates.get(i);
                if (isGoodAnchorRef(candidate, forbidden))
                    return candidate;
            }
        }
        return null;
    }

    /**
     * Choose the first good point in the given list
     * @param <T> the type of the candidates
     * @param candidates the point candidates
     * @param converter a function that convert a T to a Point.
     * @param first it true begin at the list start. If false process from the end in the reverse order.
     * @param len the number of points to test in the candidate list.
     * @param forbidden a bad reference point to avoid
     * @return the found good reference point, null if none .
     */
    @objid ("f5fa78c1-28fe-4835-a102-d7ead3be8cd9")
    public static <T> Point findGoodAnchorRef(List<T> candidates, Function<T, Point> converter, boolean first, int len, Point forbidden) {
        if (candidates==null)
            return null;
        if (candidates.isEmpty())
            return null;
        
        if (first) {
            int stop = Math.min(len, candidates.size());
            for (int i = 0; i < stop; i++) {
                Point candidate = converter.apply(candidates.get(i));
                if (isGoodAnchorRef(candidate, forbidden))
                    return candidate;
            }
        } else {
            int i = candidates.size() - 1;
            int stop = i - len;
            for (; i > stop && i >= 0; i--) {
                Point candidate = converter.apply(candidates.get(i));
                if (isGoodAnchorRef(candidate, forbidden))
                    return candidate;
            }
        }
        return null;
    }

    /**
     * Tells whether the point is a good anchor reference point.
     * <p>
     * A good anchor reference point is {@link Point} that is:<ul>
     * <li> non null
     * <li> non zero (0, 0)
     * <li> not a forbidden point, usually the center of a figure.
     * </ul>
     * @param candidate a point
     * @param forbidden a forbidden point
     * @return true if the point is a good anchor reference point.
     */
    @objid ("7b680ed6-3a18-4171-b68b-e8b674d55867")
    public static boolean isGoodAnchorRef(Point candidate, Point forbidden) {
        return candidate != null
                && !candidate.equals(0, 0)
                && !Objects.equals(candidate, forbidden) ;
        
    }

}
