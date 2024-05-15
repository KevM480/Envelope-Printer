package com.kevinm.envelopeprinter.ui.controls;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import com.kevinm.envelopeprinter.draw.DrawEnvelope;
import com.kevinm.envelopeprinter.properties.EnvelopePrinterConfig;

public class JPreviewScrollPane extends JScrollPane {

	public JPreviewScrollPane() {
		this.setBorder(BorderFactory.createLineBorder(getForeground()));

		this.setPreferredSize(new Dimension(0, 300));
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		final JEnvelopePreviewPanel previewPanel = new JEnvelopePreviewPanel();
		previewPanel.setName("preview_panel");
		previewPanel.modifyZoom();
		this.setViewportView(previewPanel);
		this.setAutoscrolls(true);
		this.addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {
			}

			@Override
			public void componentResized(ComponentEvent e) {
				previewPanel.modifyZoom();
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
	}

	public void centerViewport(boolean forceResize) {
		((JEnvelopePreviewPanel) this.getViewport().getView()).centerZoom(forceResize);
	}

	private class JEnvelopePreviewPanel extends JPanel {
		private double zoom = 1.2;
		private double prevZoom = 0;
		private Dimension envelopeSize = new Dimension();

		private JEnvelopePreviewPanel() {
			this.setEnvelopeSize(6, 3.5);

			MouseAdapter mouseAdapter = new MouseAdapter() {
				private Point origin;

				@Override
				public void mousePressed(MouseEvent e) {
					JEnvelopePreviewPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
					origin = new Point(e.getPoint());
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					JEnvelopePreviewPanel.this.setCursor(Cursor.getDefaultCursor());
				}

				@Override
				public void mouseDragged(MouseEvent e) {
					Point dragEventPoint = e.getPoint();
					JViewport viewport = JPreviewScrollPane.this.getViewport();
					Point viewPos = viewport.getViewPosition();
					int maxViewPosX = JEnvelopePreviewPanel.this.getWidth() - viewport.getWidth();
					int maxViewPosY = JEnvelopePreviewPanel.this.getHeight() - viewport.getHeight();

					// Ensures the panel is larger than the viewport. Tells if it can scroll or not.
					if (JEnvelopePreviewPanel.this.getWidth() > viewport.getWidth()) {
						// Find the distance between the origin point and the new mouse point.
						viewPos.x -= dragEventPoint.x - origin.x;

						if (viewPos.x < 0) {
							viewPos.x = 0;
							origin.x = dragEventPoint.x;
						}

						if (viewPos.x > maxViewPosX) {
							viewPos.x = maxViewPosX;
							origin.x = dragEventPoint.x;
						}
					}

					// Ensures the panel is larger than the viewport. Tells if it can scroll or not.
					if (JEnvelopePreviewPanel.this.getHeight() > viewport.getHeight()) {
						// Find the distance between the origin point and the new mouse point.
						viewPos.y -= dragEventPoint.y - origin.y;

						if (viewPos.y < 0) {
							viewPos.y = 0;
							origin.y = dragEventPoint.y;
						}

						if (viewPos.y > maxViewPosY) {
							viewPos.y = maxViewPosY;
							origin.y = dragEventPoint.y;
						}
					}

					viewport.setViewPosition(viewPos);
				}

				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					if (e.isControlDown()) {
						JEnvelopePreviewPanel.this.zoom(e.getPoint(), e.getWheelRotation());
					} else
						JPreviewScrollPane.this.dispatchEvent(e);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					JEnvelopePreviewPanel.this.requestFocus();
				}
			};

			this.addMouseMotionListener(mouseAdapter);
			this.addMouseWheelListener(mouseAdapter);
			this.addMouseListener(mouseAdapter);
			this.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}

				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_R)
						JEnvelopePreviewPanel.this.centerZoom(false);
				}
			});
			this.setFocusable(true);
		}

		private void centerZoom(boolean forceResize) {
			if (forceResize)
				this.setDefualtZoom();
			this.repaint();
			this.revalidate();
			JViewport viewport = JPreviewScrollPane.this.getViewport();
			int maxViewPosX = this.getWidth() - viewport.getWidth();
			int maxViewPosY = this.getHeight() - viewport.getHeight();
			int xCenter = (int) (maxViewPosX * 0.5);
			int yCenter = (int) (maxViewPosY * 0.5);
			JPreviewScrollPane.this.getViewport().setViewPosition(new Point(xCenter, yCenter));
		}

		private void zoom(Point point, int wheelRotation) {
			double zoomFactor = wheelRotation < 0 ? 0.05 : -0.05;
			if (this.trySetZoom(zoomFactor)) {
				JViewport viewport = JPreviewScrollPane.this.getViewport();
				Point viewPos = viewport.getViewPosition();

				int maxViewPosX = this.getWidth() - viewport.getWidth();
				int maxViewPosY = this.getHeight() - viewport.getHeight();
				// Size of the scroll area of the Scroll Pane before zooming
				Point originSize = new Point(maxViewPosX, maxViewPosY);

				// Gets the mouse's position relative to the envelope preview as a percentage
				double precentMouseX = (double) point.x / this.getWidth();
				double precentMouseY = (double) point.y / this.getHeight();

				// Modifies the zoom values and updates the screen
				modifyZoom();

				int maxZoomedViewPosX = this.getWidth() - viewport.getWidth();
				int maxZoomedViewPosY = this.getHeight() - viewport.getHeight();
				// Size of the scroll area of the Scroll Pane after zooming
				Point zoomedSize = new Point(maxZoomedViewPosX, maxZoomedViewPosY);

				// Gets the distance traveled by zooming and multiplies it by the mouse position
				viewPos.x += (zoomedSize.x - originSize.x) * precentMouseX;
				viewPos.y += (zoomedSize.y - originSize.y) * precentMouseY;

				viewport.setViewPosition(viewPos);
			}

		}

		private void setEnvelopeSize(double inchWidth, double inchHeight) {
			this.envelopeSize.width = (int) Math.round(inchWidth * 72D);
			this.envelopeSize.height = (int) Math.round(inchHeight * 72D);
		}

		private Dimension getEnvelopeSize() {
			return this.envelopeSize;
		}

		private void setDefualtZoom() {
			this.zoom = 1.2;
			this.prevZoom = 0;
			modifyZoom();
		}

		/**
		 * It is advised to use {@link #trySetZoom(double)} before calling this method.
		 * This method will modify the preview by the zoom value;
		 * 
		 */
		private void modifyZoom() {
			Dimension dim = new Dimension((int) (JPreviewScrollPane.this.getWidth() * zoom) * 2, (int) (JPreviewScrollPane.this.getSize().getHeight() * zoom) * 2);
			this.setPreferredSize(dim);
			this.setSize(dim);
			this.repaint();
			this.revalidate();
		}

		private void setPrevZoom(double prevZoom) {
			this.prevZoom = prevZoom;
		}

		/**
		 * Will check to see if the zoom is at its max or min value. If it is not it
		 * will increment the zoom and prevZoom to the new value.
		 * 
		 * @param zoomFactor Value by how much the zoom will change
		 * @return
		 */
		private boolean trySetZoom(double zoomFactor) {
			this.setPrevZoom(this.zoom);
			double zoomed = this.zoom + zoomFactor;
			if (zoomed != this.prevZoom && (zoomed > 0.5 && zoomed < 3)) {
				this.zoom = zoomed;
				return true;
			}
			return false;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			DrawEnvelope.drawExampleEnvelope(g, this.zoom, this.getEnvelopeSize(), JPreviewScrollPane.this.getSize(), EnvelopePrinterConfig.senderFont, EnvelopePrinterConfig.recieverFont);
		}

	}

}
