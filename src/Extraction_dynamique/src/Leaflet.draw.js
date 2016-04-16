/*
 * Leaflet.draw assumes that you have already included the Leaflet library.
 */

L.drawVersion = '0.3.0-dev';

L.drawLocal = {
	draw: {
		toolbar: {
			// #TODO: this should be reorganized where actions are nested in actions
			// ex: actions.undo  or actions.cancel
			actions: {
				title: 'Annuler le dessin',
				text: 'quitter'
			},
			finish: {
				title: 'Dessin terminer',
				text: 'Terminer'
			},
			undo: {
				title: 'Delete last point drawn',
				text: 'Delete last point'
			},
			buttons: {
				polyline: 'Draw a polyline',
				polygon: 'Draw a polygon',
				rectangle: 'Dessiner une emprise A utiliser comme critere geographique',
				circle: 'Draw a circle',
				marker: 'Draw a marker'
			}
		},
		handlers: {
			circle: {
				tooltip: {
					start: 'Click and drag to draw circle.'
				},
				radius: 'Radius'
			},
			marker: {
				tooltip: {
					start: 'Click map to place marker.'
				}
			},
			polygon: {
				tooltip: {
					start: 'Click to start drawing shape.',
					cont: 'Click to continue drawing shape.',
					end: 'Click first point to close this shape.'
				}
			},
			polyline: {
				error: '<strong>Error:</strong> shape edges cannot cross!',
				tooltip: {
					start: 'Click to start drawing line.',
					cont: 'Click to continue drawing line.',
					end: 'Click last point to finish line.'
				}
			},
			rectangle: {
				tooltip: {
					start: 'cliquez et dessiner l"emprise .'
				}
			},
			simpleshape: {
				tooltip: {
					end: 'Relachez la souris pour terminer le dessin.'
				}
			}
		}
	},
	edit: {
		toolbar: {
			actions: {
				save: {
					title: 'Sauvegarder les modifications.',
					text: 'Sauvegarder'
				},
				cancel: {
					title: 'Annuler l"edition, annuler toutes les modifications.',
					text: 'Annuler'
				}
			},
			buttons: {
				edit: 'Modifier l"emprise.',
				editDisabled: 'Aucune emprise A modifier.',
				remove: 'Supprimer l"emprise.',
				removeDisabled: 'Aucune emprise A supprimer.'
			}
		},
		handlers: {
			edit: {
				tooltip: {
					text: 'Drag handles, or marker to edit feature.',
					subtext: 'Click cancel to undo changes.'
				}
			},
			remove: {
				tooltip: {
					text: 'Cliquez sur l"emprise pour la supprimer'
				}
			}
		}
	}
};
