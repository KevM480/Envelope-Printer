package com.kevinm.envelopeprinter.ui.controls;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
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

public class JPreviewScrollPane extends JScrollPane {

	public JPreviewScrollPane() {
		this.setBorder(BorderFactory.createLineBorder(getForeground()));

		this.setPreferredSize(new Dimension(0, 300));
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		final JEnvelopePreviewPanel previewPanel = new JEnvelopePreviewPanel();
		this.setViewportView(previewPanel);
		this.setAutoscrolls(true);
	}

	public void centerViewport() {
		if (this.getViewport().getComponent(0) instanceof JEnvelopePreviewPanel previewPanel) {
			previewPanel.centerZoom();
		}
	}

	public Point getPointOffset(Point point) {
		Dimension d = this.getSize();
		Point p = new Point(point);
		p.x -= d.width * 0.5;
		p.y -= d.height * 0.5;

		return p;
	}

	private class JEnvelopePreviewPanel extends JPanel {
		private double zoom = 1;
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
					Point p = JPreviewScrollPane.this.getPointOffset(origin);
					JPreviewScrollPane.this.getViewport().setViewPosition(p);
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

					if (JEnvelopePreviewPanel.this.getWidth() > viewport.getWidth()) {
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

					if (JEnvelopePreviewPanel.this.getHeight() > viewport.getHeight()) {
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
					if (e.isControlDown())
						JEnvelopePreviewPanel.this.zoom(e.getPoint(), e.getWheelRotation());
					else
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
						JEnvelopePreviewPanel.this.centerZoom();
				}
			});
			this.setFocusable(true);
		}

		private void centerZoom() {
			this.setDefualtZoom();
			this.repaint();
			this.revalidate();
			Rectangle bounds = JPreviewScrollPane.this.getViewport().getViewRect();
			Dimension size = this.getSize();
			int x = (size.width - bounds.width) / 2;
			int y = (size.height - bounds.height) / 2;
			JPreviewScrollPane.this.getViewport().setViewPosition(new Point(x, y));
		}

		private void zoom(Point point, int wheelRotation) {
			double zoomFactor = wheelRotation < 0 ? 0.05 : -0.05;

			if (this.modifyZoomBy(zoomFactor)) {

			}
		}

		private void setEnvelopeSize(double inchWidth, double inchHeight) {
			this.envelopeSize.width = (int) Math.round(inchWidth * 72D);
			this.envelopeSize.height = (int) Math.round(inchHeight * 72D);
		}

		private Dimension getEnvelopeSize() {
			return this.envelopeSize;
		}

		private Dimension getZoomedEnvelopeSize() {
			Dimension dim = new Dimension(this.envelopeSize);
			dim.height *= this.zoom;
			dim.width *= this.zoom;
			return dim;
		}

		private void setDefualtZoom() {
			this.zoom = 1;
			this.prevZoom = 0;
			modifyZoomBy(1);
		}

		private boolean modifyZoomBy(double zoomFactor) {
			double zoomed = this.zoom + zoomFactor;
			this.zoom = (zoomed > 0.5 && zoomed < 3) ? zoomed : this.zoom;
			boolean canZoom = this.canZoom();
			if (canZoom) {

				Dimension dim = new Dimension((int) (JPreviewScrollPane.this.getSize().width * zoom),
						(int) (JPreviewScrollPane.this.getSize().height * zoom));
				this.setPreferredSize(dim);
				this.setSize(dim);
				this.repaint();
				this.revalidate();
			}
			this.setPrevZoom(this.zoom);
			return canZoom;
		}

		private double getZoom() {
			return this.zoom;
		}

		private void setPrevZoom(double prevZoom) {
			this.prevZoom = prevZoom;
		}

		private double getPrevZoom() {
			return this.prevZoom;
		}

		private boolean canZoom() {
			return this.zoom != this.prevZoom;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			DrawEnvelope.drawExampleEnvelope(g, this.zoom, this.getEnvelopeSize(), JPreviewScrollPane.this.getSize(),
					new Font("Arial", Font.PLAIN, 8), new Font("Arial", Font.PLAIN, 8));

		}

	}

}
