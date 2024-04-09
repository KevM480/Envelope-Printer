package com.kevinm.envelopeprinter.ui.controls;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.kevinm.envelopeprinter.draw.DrawEnvelope;

public class JPreviewScrollPane extends JScrollPane {
	final JEnvelopePreviewPanel previewPanel = new JEnvelopePreviewPanel();

	public JPreviewScrollPane() {
		this.setBorder(BorderFactory.createLineBorder(getForeground()));

		this.setPreferredSize(new Dimension(0, 500));
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);

		this.setViewportView(previewPanel);

		this.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.isControlDown()) {
					JPreviewScrollPane.this.setWheelScrollingEnabled(false);
					JPreviewScrollPane.this.zoom(e.getPoint(), e.getWheelRotation());
				} else
					JPreviewScrollPane.this.setWheelScrollingEnabled(true);
			}
		});
	}

	private void zoom(Point point, int wheelRotation) {
		double zoomFactor = wheelRotation > 0 ? 0.9 : 1.1;
		this.previewPanel.setZoom(zoomFactor);

		if (this.previewPanel.canZoom()) {
			Point pos = this.getViewport().getViewPosition();

			int newX = (int) (point.x * (zoomFactor - 1) + zoomFactor * pos.x);
			int newY = (int) (point.y * (zoomFactor - 1) + zoomFactor * pos.y);
			this.getViewport().setViewPosition(new Point(newX, newY));

			this.previewPanel.repaint();
			this.previewPanel.revalidate();
		}
		this.previewPanel.setPrevZoom(this.previewPanel.getZoom());
	}

	public class JEnvelopePreviewPanel extends JPanel {
		private double zoom = 1;
		private double prevZoom = 0;

		public void setZoom(double zoomFactor) {
			double zoomed = this.zoom * zoomFactor;
			this.zoom = (zoomed > 0.5 && zoomed < 2) ? zoomed : this.zoom;

		}

		public double getZoom() {
			return this.zoom;
		}

		public void setPrevZoom(double prevZoom) {
			this.prevZoom = prevZoom;
		}

		public double getPrevZoom() {
			return this.prevZoom;
		}

		private boolean canZoom() {
			return this.zoom != this.prevZoom;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Dimension dim = DrawEnvelope.drawExampleEnvelope(g, this.zoom, 5, 5, getFont(), getFont());
			this.setPreferredSize(dim);
			this.setSize(dim);
		}

	}

}
