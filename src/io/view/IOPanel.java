package io.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import io.controller.IOController;
import io.model.Game;

import javax.swing.*;

public class IOPanel extends JPanel
{

	private IOController baseController;
	private JButton saveButton;
	private JTextField titleField;
	private JTextField rankingField;
	private JTextArea rulesArea;
	private JLabel rulesLabel;
	private JLabel rankingLabel;
	private JLabel titleLabel;
	private SpringLayout baseLayout;
	private JButton loadButton;
	private JLabel gameCountLabel;

	public IOPanel(IOController baseController)
	{
		this.baseController = baseController;
		

		saveButton = new JButton("save the game stuff");
		titleField = new JTextField(15);
		titleLabel = new JLabel("Game Title:");
		rankingField = new JTextField(5);
		rankingLabel = new JLabel("Game Ranking:");
		rulesArea = new JTextArea(5, 20);
		rulesLabel = new JLabel("Game Rules:");
		gameCountLabel = new JLabel("Current game count:");
		baseLayout = new SpringLayout();
		loadButton = new JButton("Load Stuff");
	

		setupPanel();
		setupLayout();
		setupListeners();
	}

	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(rankingField);
		this.add(rankingLabel);
		this.add(rulesArea);
		this.add(rulesLabel);
		this.add(saveButton);
		this.add(titleField);
		this.add(titleLabel);
		this.add(loadButton);
		this.add(gameCountLabel);
	}

	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.SOUTH, saveButton, -29, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, saveButton, -210, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesArea, 0, SpringLayout.NORTH, rulesLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rulesArea, 6, SpringLayout.EAST, rulesLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesLabel, 24, SpringLayout.SOUTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rulesLabel, 0, SpringLayout.WEST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingField, 0, SpringLayout.NORTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rankingField, 6, SpringLayout.EAST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingLabel, 19, SpringLayout.SOUTH, titleField);
		baseLayout.putConstraint(SpringLayout.WEST, rankingLabel, 0, SpringLayout.WEST, titleLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, titleField, -3, SpringLayout.NORTH, titleLabel);
		baseLayout.putConstraint(SpringLayout.WEST, titleField, 6, SpringLayout.EAST, titleLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, titleLabel, 21, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, titleLabel, 76, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, saveButton);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 39, SpringLayout.EAST, saveButton);
		baseLayout.putConstraint(SpringLayout.NORTH, gameCountLabel, 19, SpringLayout.SOUTH, rulesArea);
		baseLayout.putConstraint(SpringLayout.EAST, gameCountLabel, 0, SpringLayout.EAST, rankingLabel);
	}

	private void setupListeners()
	{
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.makeGameFromInput(
						titleField.getText(), rankingField.getText(),
						rulesArea.getText());
				if (tempGame != null)
				{
					baseController.saveGameInformation(tempGame);
					gameCountLabel.setText("Current game count: " + baseController.getProjectGames().size());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "try again with a valid number");
				}
			}
		});

		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.readGameInformation();
				if (tempGame != null)
				{
					titleField.setText(tempGame.getGameTitle());
					rankingField.setText(Integer.toString(tempGame
							.getFunRanking()));
					String tempRules = "";
					for (String currentRule : tempGame.getGameRules())
					{
						tempRules += currentRule + "\n";
					}
					rulesArea.setText(tempRules);
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"Check the Save file, make sure it is in order.");
				}
			}
		});
	}
}
