package io.view;

import io.controller.IOController;

import javax.swing.JFrame;

/**
 * Frame for the IO project
 * @author ko'l0290
 *
 */
public class IOFrame extends JFrame
{
	/**
	 * IOPanel for the frame.
	 */
	private IOPanel basePanel;
	
	/**
	 * Constructor for the IOFrame.
	 * @param baseController IOController passed in for MVC relationship.
	 */
	
	public IOFrame(IOController baseController)
	{
		basePanel = new IOPanel(baseController);
		
		setupFrame();
	}
	
	/**
	 * Sets up the frame size and loads the content panel.
	 */
	
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setSize(400, 400);
		this.setVisible(true);
	}
}
