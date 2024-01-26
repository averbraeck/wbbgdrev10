package nl.tudelft.pa.wbtransport;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.rmi.RemoteException;

import javax.naming.NamingException;

import org.opentrafficsim.core.animation.ClonableRenderable2DInterface;
import org.opentrafficsim.core.animation.TextAlignment;
import org.opentrafficsim.core.dsol.OTSSimulatorInterface;
import org.opentrafficsim.core.network.animation.PaintLine;

import nl.tudelft.simulation.dsol.animation.Locatable;
import nl.tudelft.simulation.dsol.animation.D2.Renderable2D;

/**
 * Draws a RoadZ.
 * <p>
 * Copyright (c) 2013-2016 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://opentrafficsim.org/docs/license.html">OpenTrafficSim License</a>.
 * <p>
 * $LastChangedDate: 2017-01-16 01:48:07 +0100 (Mon, 16 Jan 2017) $, @version $Revision: 3281 $, by $Author: averbraeck $,
 * initial version Sep 13, 2014 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class RoadZAnimation extends Renderable2D implements ClonableRenderable2DInterface, Serializable
{
    /** */
    private static final long serialVersionUID = 20140000L;

    /** */
    private float width;

    /** */
    final String printName;

    /**
     * @param roadZ RoadZ
     * @param printName name to print for the road
     * @param simulator simulator
     * @param width width
     * @throws NamingException for problems with registering in context
     * @throws RemoteException on communication failure
     */
    public RoadZAnimation(final RoadZ roadZ, final String printName, final OTSSimulatorInterface simulator, final float width)
            throws NamingException, RemoteException
    {
        super(roadZ, simulator);
        this.width = width;
        this.printName = printName;

        RoadZTextAnimation wta =
                new RoadZTextAnimation(roadZ, printName, 0.0f, 15.0f, TextAlignment.CENTER, Color.BLACK, 10.0f, simulator);
        // wta.setRotate(false);
        wta.setScale(false);
    }

    /** {@inheritDoc} */
    @Override
    public final void paint(final Graphics2D graphics, final ImageObserver observer) throws RemoteException
    {
        PaintLine.paintLine(graphics, Color.BLACK, this.width, getSource().getLocation(),
                ((RoadZ) getSource()).getDesignLine());
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("checkstyle:designforextension")
    public ClonableRenderable2DInterface clone(final Locatable newSource, final OTSSimulatorInterface newSimulator)
            throws NamingException, RemoteException
    {
        // the constructor also constructs the corresponding Text object
        return new RoadZAnimation((RoadZ) newSource, this.printName, newSimulator, this.width);
    }

    /** {@inheritDoc} */
    @Override
    public final String toString()
    {
        return "RoadZAnimation [width=" + this.width + ", link=" + super.getSource() + "]";
    }

}
