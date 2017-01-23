package com.xiuye.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

public class OpenFileFrame extends JFrame {

	private JMenuBar menuBar;
	private JTextArea area;
	private JLabel fileNameLabel;
	private JTextField fileNameTextField;
	private JFileChooser fileChooser;
	private JProgressBar progressBar;
	private JPanel southPanel;

	private static final int WIDTH = 700;
	private static final int HEIGHT = 700;

	private CurrentControlState state;

	/**
	 *
	 */
	private static final long serialVersionUID = 4296680701211544402L;

	private static final String BEGINNING = "1";
	private static final String PAUSE = "2";
	private static final String END = "3";
	private static final String CONTINUE = "4";
	private static final String RUNNING = "5";

	class CurrentControlState {
		private Object control;
		private String state;

		private Thread t;

		public Thread getT() {
			return t;
		}

		public void setT(Thread t) {
			this.t = t;
		}

		public CurrentControlState() {
		}

		public CurrentControlState(Object control, String state) {
			this.control = control;
			this.state = state;
		}

		public Object getControl() {
			return control;
		}

		public void setControl(Object control) {
			this.control = control;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		@Override
		public String toString() {
			return "CurrentControlState [control=" + control + ", state=" + state + ", t=" + t + "]";
		}

	}

	public OpenFileFrame() {
		this.init();
	}

	private void init() {
		this.initLookAndFeel();
		this.initMenu();
		this.initFileChooser();
		this.initTextArea();
		this.initLabels();
		this.initSouthPanel();
		this.initFrame();
	}

	private void initSouthPanel() {
		this.southPanel = new JPanel();
		this.initProgressBar();
		this.initButtons();
		this.getContentPane().add(this.southPanel, BorderLayout.CENTER);
	}

	private void initLabels() {

		this.fileNameLabel = new JLabel("文件名字:");
		this.fileNameTextField = new JTextField();
		this.fileNameTextField.setEditable(false);
		this.fileNameTextField.setColumns(100);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(fileNameLabel);
		panel.add(fileNameTextField);
		this.getContentPane().add(panel, BorderLayout.NORTH);

	}

	private void initProgressBar() {
		this.progressBar = new JProgressBar();
		this.progressBar.setOrientation(JProgressBar.HORIZONTAL);
		this.progressBar.setMinimum(0);
		this.progressBar.setMaximum(100);
		this.progressBar.setValue(0);
		this.progressBar.setStringPainted(true);// 显示进度字符串
		this.progressBar.setPreferredSize(new Dimension(WIDTH, 30));
		this.southPanel.add(progressBar, BorderLayout.NORTH);

	}

	private static final String commandImportDataStart = "开始导入数据";
	private static final String commandImportDataPause = "暂停导入数据";
	private static final String commandImportDataContinue = "继续导入数据";
	private static final String commandImportDataEnd = "结束导入数据";
	private static final String commandImportDataExit = "退出程序";
	private static final String commandImportDataClear = "清理显示";
	private static final String commandImportDataClearTable = "清空数据库数据";

	private void initButtons() {

		JButton start = createButton(commandImportDataStart);
		start.setActionCommand(BEGINNING);
		start.setForeground(Color.BLUE);
		start.setBackground(Color.BLUE);
		// JButton pause = createButton(commandImportDataPause);
		JButton end = createButton(commandImportDataEnd);
		end.setForeground(Color.RED);
		end.setEnabled(false);
		JButton exit = createButton(commandImportDataExit);
		JButton clearUIControlData = createButton(commandImportDataClear);
		JButton truncateTables = createButton(commandImportDataClearTable);

		this.buttonAddListener(start, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JButton b = (JButton) e.getSource();

				String command = b.getActionCommand();

				String path = fileNameTextField.getText();

				// check file exist?
				File f = new File(path);
				if (!f.exists()) {
					JOptionPane.showMessageDialog(OpenFileFrame.this, "文件已经不存在了!");
					return;
				}

				// validate state?
				if (state == null) {
					state = createCurrentControlState(b, command);
				}
				switch (command) {
				case BEGINNING:
					int result = JOptionPane.showConfirmDialog(OpenFileFrame.this, "确定导入数据...", "导入数据",
							JOptionPane.OK_CANCEL_OPTION);
					if (result != JOptionPane.OK_OPTION) {
						return;
					}
					end.setEnabled(true);
					clearUIControlData.setEnabled(true);
					b.setForeground(Color.GREEN);
					Thread t = new Thread() {
						@Override
						public void run() {

							try (FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr)) {
								area.setText(null);
								String s = null;
								int i = 0;
								long fileSize = f.length();
								double importSize = 0.0;
								while ((s = br.readLine()) != null) {
									i++;
									if (i % 10 == 0) {
										area.setText(null);
									} else {
										area.append(s + "\n");
										insertDataToDB();
										importSize += s.getBytes().length;
										int value = (int) (importSize / fileSize * 100);
										progressBar.setValue(value);
									}
									switch (b.getActionCommand()) {
									case END:
										progressBar.setValue(0);
										fileNameTextField.setText(null);
										state = null;
										b.setActionCommand(BEGINNING);
										b.setText(commandImportDataStart);
										end.setEnabled(false);
										return;
									case PAUSE:
										while (b.getActionCommand() == PAUSE)
											;
										break;
									}
									System.out.println(b.getActionCommand());
								}

							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							progressBar.setValue(100);
							JOptionPane.showMessageDialog(OpenFileFrame.this, "数据导入完成!");
							// reset
							progressBar.setValue(0);
							fileNameTextField.setText(null);
							state = null;
							b.setActionCommand(BEGINNING);
							b.setText(commandImportDataStart);
							end.setEnabled(false);
							b.setForeground(Color.BLUE);
						}

					};
					t.setPriority(Thread.MAX_PRIORITY);
					t.start();
					// EventQueue.invokeLater(t);
					state.setT(t);
					b.setActionCommand(RUNNING);
					state.setState(RUNNING);
					b.setText(commandImportDataPause);
					break;
				case RUNNING:
					b.setActionCommand(PAUSE);
					state.setState(PAUSE);
					b.setText(commandImportDataContinue);
					break;
				case PAUSE:
					b.setActionCommand(CONTINUE);
					state.setState(CONTINUE);
					b.setText(commandImportDataPause);
					break;
				case CONTINUE:
					b.setActionCommand(PAUSE);
					state.setState(PAUSE);
					b.setText(commandImportDataContinue);
					break;
				}

			}
		});

		this.buttonAddListener(end, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				start.setActionCommand(END);

			}
		});
		this.buttonAddListener(exit, this.exitListener());
		this.buttonAddListener(clearUIControlData, this.clearUIControlListener());

		JPanel panel = new JPanel(new GridLayout(5, 2));

		JLabel label = this.createLabel("表:");
		label.setForeground(Color.ORANGE);
		panel.add(label);

		JComboBox<String> comboBox = this.createComboBox();
		comboBox.addItem("--请选择--");
		panel.add(comboBox);

		panel.add(start);
		panel.add(end);
		// panel.add(pause);
		panel.add(clearUIControlData);
		panel.add(truncateTables);
		panel.add(exit);

		this.southPanel.add(panel, BorderLayout.CENTER);
	}

	private JLabel createLabel(String str) {
		JLabel label = new JLabel(str, JLabel.CENTER);
		label.setFont(new Font(label.getFont().getFontName(), Font.BOLD, 25));
		return label;
	}

	private <T> JComboBox<T> createComboBox() {

		JComboBox<T> comboBox = new JComboBox<>();
		comboBox.setFont(new Font(comboBox.getFont().getFontName(), Font.BOLD, 25));
		return comboBox;

	}

	private ActionListener clearUIControlListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText(null);
			}
		};
	}

	private ActionListener exitListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		};
	}

	private CurrentControlState createCurrentControlState(Object control, String state) {
		return new CurrentControlState(control, state);
	}

	static int i = 0;

	private void insertDataToDB() {

		System.out.println("insert data to db : " + i++);
	}

	private JButton createButton(String buttonName) {

		JButton b = new JButton(buttonName);

		b.setFont(new Font(b.getFont().getFontName(), Font.BOLD, 25));

		return b;

	}

	private void buttonAddListener(JButton button, ActionListener listener) {
		button.addActionListener(listener);
	}

	private void initLookAndFeel() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

	}

	private void initTextArea() {
		this.area = new JTextArea(10, 35);

		Font font = new Font(this.area.getFont().getFontName(), Font.BOLD, 24);
		this.area.setFont(font);
		JScrollPane jScrollPane = new JScrollPane(area);
		this.getContentPane().add(jScrollPane, BorderLayout.SOUTH);
	}

	private void initMenu() {
		JMenu menuOperation = new JMenu("操作");
		JMenu menuConfiguration = new JMenu("配置");

		this.menuBar = new JMenuBar();
		JMenuItem open = new JMenuItem("打开");

		JMenuItem addConfig = new JMenuItem("新建");
		JMenuItem alterConfig = new JMenuItem("修改");
		JMenuItem deleteConfig = new JMenuItem("删除");

		addConfig.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(OpenFileFrame.this, "打开配置文件窗口!");

			}
		});

		this.MenuItemAddListener(open, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fileNameTextField.setText(null);
				if (fileChooser.showOpenDialog(OpenFileFrame.this) == JFileChooser.APPROVE_OPTION) {
					File f = fileChooser.getSelectedFile();
					fileNameTextField.setText(f.getAbsolutePath());

				}
			}

		});

		menuOperation.add(open);
		menuConfiguration.add(addConfig);
		menuConfiguration.add(alterConfig);
		menuConfiguration.add(deleteConfig);
		this.menuBar.add(menuOperation);
		this.menuBar.add(menuConfiguration);
		this.setJMenuBar(menuBar);

	}

	private void initFileChooser() {
		File f = null;
		String filePath = System.getProperty("user.home");
		if (filePath == null) {
			filePath = ".";
			f = new File(filePath);
		} else {
			filePath += File.separator + "desktop";
			f = new File(filePath);
			if (!f.exists()) {
				filePath += File.separator + "桌面";
				f = new File(filePath);
			}
		}
		this.fileChooser = new JFileChooser(f);
		this.fileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "*.csv";
			}

			@Override
			public boolean accept(File f) {

				return f.isDirectory() || f.getName().endsWith(".csv");
			}
		});
	}

	private void MenuItemAddListener(JMenuItem item, ActionListener l) {
		item.addActionListener(l);
	}

	public void display() {
		this.setVisible(true);
	}

	private void initFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	public static void main(String[] args) {
		OpenFileFrame app = new OpenFileFrame();
		app.display();

	}

}
